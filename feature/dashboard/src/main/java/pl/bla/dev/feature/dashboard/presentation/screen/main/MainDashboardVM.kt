package pl.bla.dev.feature.dashboard.presentation.screen.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.feature.dashboard.presentation.screen.main.mapper.MainDashboardDialogMapper
import pl.bla.dev.feature.dashboard.presentation.screen.main.mapper.MainDashboardDialogMapper.DialogType
import pl.bla.dev.feature.dashboard.presentation.screen.main.mapper.MainDashboardScreenMapper
import pl.bla.dev.feature.dashboard.presentation.screen.main.model.BottomNavItem
import javax.inject.Inject

interface MainDashboardVM {
  sealed class State(val selectedItem: Int) {
    data object Initial : State(selectedItem = -1)
    data object MapScreen : State(selectedItem = 0)
    data object TravelScreen : State(selectedItem = 1)
    data object SettingsScreen : State(selectedItem = 2)
  }

  sealed interface Action {
    sealed interface Navigation {
      data object Logout : Navigation
      data class ShowDialog(
        val dialogData: DialogData,
      ) : Navigation
    }

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
    data class Initial(
      override val onBackClick: () -> Unit,
    ) : ScreenData(
      bottomNavItems = emptyList(),
      onBackClick = onBackClick,
    )
    data class MapScreen(
      override val bottomNavItems: List<BottomNavItem>,
      override val onBackClick: () -> Unit,
    ) : ScreenData(
      bottomNavItems = bottomNavItems,
      onBackClick = onBackClick,
      selectedItem = 0,
    )
    data class TravelScreen(
      override val bottomNavItems: List<BottomNavItem>,
      override val onBackClick: () -> Unit,
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
) : CustomViewModel<MainDashboardVM.State, MainDashboardVM.ScreenData, MainDashboardVM.Action.Navigation>(
  initialStateValue = MainDashboardVM.State.MapScreen,
), MainDashboardVM {
  override val screenData: StateFlow<MainDashboardVM.ScreenData> = _screenData

  init {
    initState()
  }

  override suspend fun onStateEnter(newState: MainDashboardVM.State) {}

  override fun mapScreenData(): MainDashboardVM.ScreenData = mainDashboardScreenMapper(
    params = MainDashboardScreenMapper.Params(
      state = state.value,
      onBackClick = {
        dispatchAction(MainDashboardVM.Action.ShowDialog(dialogType = DialogType.Logout))
      },
      onBottomNavItemClick = { id ->
        dispatchAction(MainDashboardVM.Action.OnBottomNavItemClick(id = id))
      }
    )
  )

  private fun dispatchAction(action: MainDashboardVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        is MainDashboardVM.State.Initial -> when (action) {
          is MainDashboardVM.Action.ShowDialog -> showDialog(dialogType = action.dialogType)
          is MainDashboardVM.Action.OnBottomNavItemClick -> onBottomNavItemClick(
            state = currentState,
            id = action.id,
          )
          is MainDashboardVM.Action.Logout -> MainDashboardVM.Action.Navigation.Logout.emit()
        }
        is MainDashboardVM.State.MapScreen -> when (action) {
          is MainDashboardVM.Action.ShowDialog -> showDialog(dialogType = action.dialogType)
          is MainDashboardVM.Action.OnBottomNavItemClick -> onBottomNavItemClick(
            state = currentState,
            id = action.id,
          )
          is MainDashboardVM.Action.Logout -> MainDashboardVM.Action.Navigation.Logout.emit()
        }
        is MainDashboardVM.State.TravelScreen -> when (action) {
          is MainDashboardVM.Action.ShowDialog -> showDialog(dialogType = action.dialogType)
          is MainDashboardVM.Action.OnBottomNavItemClick -> onBottomNavItemClick(
            state = currentState,
            id = action.id,
          )
          is MainDashboardVM.Action.Logout -> MainDashboardVM.Action.Navigation.Logout.emit()
        }
        is MainDashboardVM.State.SettingsScreen -> when (action) {
          is MainDashboardVM.Action.ShowDialog -> showDialog(dialogType = action.dialogType)
          is MainDashboardVM.Action.OnBottomNavItemClick -> onBottomNavItemClick(
            state = currentState,
            id = action.id,
          )
          is MainDashboardVM.Action.Logout -> MainDashboardVM.Action.Navigation.Logout.emit()
        }
      }
    }
  }

  private fun onBottomNavItemClick(state: MainDashboardVM.State, id: Int) {
    if (state.selectedItem == id) return

    when (id) {
      0 -> MainDashboardVM.State.MapScreen.override()
      1 -> MainDashboardVM.State.TravelScreen.override()
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