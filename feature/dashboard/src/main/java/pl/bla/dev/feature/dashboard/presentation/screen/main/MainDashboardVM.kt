package pl.bla.dev.feature.dashboard.presentation.screen.main

import android.location.Location
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.common.core.logger.Log
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.intents.domain.usecase.OpenAppSettingsIntentUC
import pl.bla.dev.common.permission.PermissionsManager
import pl.bla.dev.common.permission.domain.model.AppPermission
import pl.bla.dev.common.permission.domain.model.PermissionResult
import pl.bla.dev.common.sensor.usecase.GetCurrentLocationUC
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.common.ui.componenst.permissions.PermissionRequesterData
import pl.bla.dev.feature.dashboard.presentation.screen.main.mapper.MainDashboardDialogMapper
import pl.bla.dev.feature.dashboard.presentation.screen.main.mapper.MainDashboardDialogMapper.DialogType
import pl.bla.dev.feature.dashboard.presentation.screen.main.mapper.MainDashboardScreenMapper
import pl.bla.dev.feature.dashboard.presentation.screen.main.model.BottomNavItem
import pl.bla.dev.feature.dashboard.presentation.screen.main.model.TravelShortDisplayData
import pl.bla.dev.feature.settings.contract.domain.model.TravelShortData
import pl.bla.dev.feature.settings.contract.domain.usecase.GetUserTravelsShortDataUC
import javax.inject.Inject

interface MainDashboardVM {
  sealed class State(val selectedItem: Int) {
    data class MapScreen(
      val permissionResult: PermissionResult = PermissionResult.DENIED,
      val currentLocation: Location? = null,
    ) : State(selectedItem = 0)
    data class TravelScreen(
      val travelsShortData: List<TravelShortData> = emptyList(),
    ) : State(selectedItem = 1)
    data object SettingsScreen : State(selectedItem = 2)
  }

  sealed interface Action {
    sealed interface Navigation {
      data object Logout : Navigation
      data class ShowDialog(
        val dialogData: DialogData,
      ) : Navigation
      data class ToTravelDetails(val travelId: Int) : Navigation
      data object ToNewTravel : Navigation
    }

    data object Back : Action
    data object OnFABClick : Action
    data class ToTravelDetails(val travelId: Int) : Action
    data object RequestLocationPermission : Action
    data object OpenAppSettings : Action
    data class OnBottomNavItemClick(
      val id: Int,
    ): Action
    data object Logout : Action
    data class ShowDialog(
      val dialogType: DialogType,
    ) : Action
  }

  sealed class ScreenData(
    open val bottomNavItems: List<BottomNavItem>,
    open val onBackClick: () -> Unit,
    val selectedItem: Int = -1,
  ) {
    data class MapScreen(
      override val bottomNavItems: List<BottomNavItem>,
      override val onBackClick: () -> Unit,
      val currentLocation: Location?,
      val permissionRequesterData: PermissionRequesterData?,
      val onFABClick: () -> Unit,
    ) : ScreenData(
      bottomNavItems = bottomNavItems,
      onBackClick = onBackClick,
      selectedItem = 0,
    )
    data class TravelScreen(
      override val bottomNavItems: List<BottomNavItem>,
      override val onBackClick: () -> Unit,
      val futureTravels: List<TravelShortDisplayData>,
      val pastTravels: List<TravelShortDisplayData>,
      val cancelledTravels: List<TravelShortDisplayData>,
      val currentTravels: List<TravelShortDisplayData>,
    ) : ScreenData(
      bottomNavItems = bottomNavItems,
      onBackClick = onBackClick,
      selectedItem = 1,
    )
    data class SettingsScreen(
      override val bottomNavItems: List<BottomNavItem>,
      override val onBackClick: () -> Unit,
    ) : ScreenData(
      bottomNavItems = bottomNavItems,
      onBackClick = onBackClick,
      selectedItem = 2,
    )
  }

  val screenData: StateFlow<ScreenData>
}


@HiltViewModel
class MainDashboardVMImpl @Inject constructor(
  private val mainDashboardScreenMapper: MainDashboardScreenMapper,
  private val mainDashboardDialogMapper: MainDashboardDialogMapper,
  private val getCurrentLocationUC: GetCurrentLocationUC,
  private val permissionsManager: PermissionsManager,
  private val openAppSettingsUC: OpenAppSettingsIntentUC,
  private val getUserTravelsShortDataUC: GetUserTravelsShortDataUC,
) : CustomViewModel<MainDashboardVM.State, MainDashboardVM.ScreenData, MainDashboardVM.Action.Navigation>(
  initialStateValue = MainDashboardVM.State.MapScreen(),
), MainDashboardVM {
  override val screenData: StateFlow<MainDashboardVM.ScreenData> = _screenData

  init {
    initState()
  }

  override suspend fun onStateEnter(newState: MainDashboardVM.State) {
    when (newState) {
      is MainDashboardVM.State.MapScreen -> {
        val result = permissionsManager.requestPermission(
          permission = AppPermission.LOCATION,
        )

        when (result) {
          PermissionResult.GRANTED -> {
            getCurrentLocationUC(param = UseCase.Params.Empty).fold(
              onRight = { location ->
                newState.copy(
                  currentLocation = location,
                  permissionResult = result,
                ).mutate()
              },
              onLeft = {
                //todo error handling
                Log.e("MainDashboardVMImpl", "onStateEnter: ${it.exception}")
              }
            )
          }
          PermissionResult.DENIED,
          PermissionResult.DENIED_FOREVER -> {
            newState.copy(
              permissionResult = result,
            ).mutate()
          }
        }
      }
      is MainDashboardVM.State.TravelScreen -> {
        getUserTravelsShortDataUC(UseCase.Params.Empty).fold(
          onRight = { travels ->
            newState.copy(
              travelsShortData =  travels,
            ).mutate()
          },
          onLeft = {
            //todo error handling
          }
        )
      }
      else -> {}
    }
  }

  override fun mapScreenData(): MainDashboardVM.ScreenData = mainDashboardScreenMapper(
    params = MainDashboardScreenMapper.Params(
      state = state.value,
      onBackClick = {
        dispatchAction(MainDashboardVM.Action.Back)
      },
      onBottomNavItemClick = { id ->
        dispatchAction(MainDashboardVM.Action.OnBottomNavItemClick(id = id))
      },
      onOpenAppSettings = {
        dispatchAction(MainDashboardVM.Action.OpenAppSettings)
      },
      onRequestPermission = {
        dispatchAction(MainDashboardVM.Action.RequestLocationPermission)
      },
      onTravelClick = { id ->
        dispatchAction(MainDashboardVM.Action.ToTravelDetails(travelId = id))
      },
      onFABClick = {
        dispatchAction(MainDashboardVM.Action.OnFABClick)
      }
    )
  )

  private fun dispatchAction(action: MainDashboardVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        is MainDashboardVM.State.MapScreen -> when (action) {
          is MainDashboardVM.Action.Back -> dispatchAction(MainDashboardVM.Action.ShowDialog(dialogType = DialogType.Logout))
          is MainDashboardVM.Action.ShowDialog -> showDialog(dialogType = action.dialogType)
          is MainDashboardVM.Action.OnBottomNavItemClick -> onBottomNavItemClick(
            state = currentState,
            id = action.id,
          )
          is MainDashboardVM.Action.Logout -> MainDashboardVM.Action.Navigation.Logout.emit()
          is MainDashboardVM.Action.RequestLocationPermission -> {
            val result = permissionsManager.requestPermission(
              permission = AppPermission.LOCATION,
            )

            when (result) {
              PermissionResult.GRANTED -> {
                getCurrentLocationUC(param = UseCase.Params.Empty).fold(
                  onRight = { location ->
                    currentState.copy(
                      currentLocation = location,
                      permissionResult = result,
                    ).mutate()
                  },
                  onLeft = {
                    //todo error handling
                    Log.e("MainDashboardVMImpl", "onStateEnter: ${it.exception}")
                  }
                )
              }
              PermissionResult.DENIED,
              PermissionResult.DENIED_FOREVER -> {
                currentState.copy(
                  permissionResult = result,
                ).mutate()
              }
            }
          }
          is MainDashboardVM.Action.OpenAppSettings -> {
            openAppSettingsUC(UseCase.Params.Empty)
            currentState.copy(
              permissionResult = PermissionResult.DENIED,
            ).mutate()
          }
          is MainDashboardVM.Action.OnFABClick -> MainDashboardVM.Action.Navigation.ToNewTravel.emit()
          is MainDashboardVM.Action.ToTravelDetails -> {}
        }
        is MainDashboardVM.State.TravelScreen -> when (action) {
          is MainDashboardVM.Action.Back -> MainDashboardVM.State.MapScreen().override()
          is MainDashboardVM.Action.ShowDialog -> showDialog(dialogType = action.dialogType)
          is MainDashboardVM.Action.OnBottomNavItemClick -> onBottomNavItemClick(
            state = currentState,
            id = action.id,
          )
          is MainDashboardVM.Action.Logout -> MainDashboardVM.Action.Navigation.Logout.emit()
          is MainDashboardVM.Action.ToTravelDetails ->
            MainDashboardVM.Action.Navigation.ToTravelDetails(travelId = action.travelId).emit()
          is MainDashboardVM.Action.RequestLocationPermission -> {}
          is MainDashboardVM.Action.OpenAppSettings -> {}
          is MainDashboardVM.Action.OnFABClick -> {}
        }
        is MainDashboardVM.State.SettingsScreen -> when (action) {
          is MainDashboardVM.Action.Back -> MainDashboardVM.State.MapScreen().override()
          is MainDashboardVM.Action.ShowDialog -> showDialog(dialogType = action.dialogType)
          is MainDashboardVM.Action.OnBottomNavItemClick -> onBottomNavItemClick(
            state = currentState,
            id = action.id,
          )
          is MainDashboardVM.Action.Logout -> MainDashboardVM.Action.Navigation.Logout.emit()
          is MainDashboardVM.Action.RequestLocationPermission -> {}
          is MainDashboardVM.Action.OpenAppSettings -> {}
          is MainDashboardVM.Action.ToTravelDetails -> {}
          is MainDashboardVM.Action.OnFABClick -> {}
        }
      }
    }
  }

  private fun onBottomNavItemClick(state: MainDashboardVM.State, id: Int) {
    if (state.selectedItem == id) return

    when (id) {
      0 -> MainDashboardVM.State.MapScreen().override()
      1 -> MainDashboardVM.State.TravelScreen().override()
      2 -> MainDashboardVM.State.SettingsScreen.override()
    }
  }

  private fun showDialog(dialogType: DialogType) {
    MainDashboardVM.Action.Navigation.ShowDialog(
      dialogData = mainDashboardDialogMapper(
        params = MainDashboardDialogMapper.Params(
          type = dialogType,
          onLogoutClick = {
            dispatchAction(MainDashboardVM.Action.Logout)
          }
        )
      )
    ).emit()
  }
}