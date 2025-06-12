package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleType
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.common.ui.componenst.select.SelectData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.settings.contract.domain.usecase.GetSavedNewTravelConfigUC
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.NewTravelOriginVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper.NewTravelVehicleDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper.NewTravelVehicleDialogMapper.DialogType
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper.NewTravelVehicleScreenMapper
import javax.inject.Inject

interface NewTravelVehicleVM {
  sealed interface State {
    data object Initial : State
    data class Initialized(
      val newTravelConfig: NewTravelConfig,
      val selectedOriginVehicleType: VehicleType = VehicleType.PLANE,
    ) : State
  }

  sealed interface Action {
    sealed interface Navigation {
      data object Back : Navigation
      data class ShowDialog(
        val dialogData: DialogData,
      ) : Navigation
      data class ToOrigin(
        val setupData: NewTravelOriginVM.NewTravelSetupData,
      ) : Navigation
    }

    data class UpdateSelectedOriginVehicleType(
      val vehicleId: Int,
    ) : Action
    data object OnNextClick : Action
    data class ShowDialog(
      val dialogType: DialogType,
    ) : Action
    data object Back : Action
  }

  sealed class ScreenData(
    open val onBackClick: () -> Unit,
    open val topAppBarData: TopAppBarData,
  ) {
    data class Initial(
      override val topAppBarData: TopAppBarData,
      override val onBackClick: () -> Unit,
    ) : ScreenData(
      topAppBarData = topAppBarData,
      onBackClick = onBackClick,
    )

    data class Initialized(
      override val topAppBarData: TopAppBarData,
      override val onBackClick: () -> Unit,
      val screenTitle: String,
      val vehicleTypeSelectData: SelectData,
      val nextButtonData: LargeButtonData.Primary,
    ) : ScreenData(
      topAppBarData = topAppBarData,
      onBackClick = onBackClick,
    )
  }

  val screenData: StateFlow<ScreenData>
}


@HiltViewModel
class NewTravelVehicleVMImpl @Inject constructor(
  private val newTravelVehicleScreenMapper: NewTravelVehicleScreenMapper,
  private val newTravelVehicleDialogMapper: NewTravelVehicleDialogMapper,
  private val getSavedNewTravelConfigUC: GetSavedNewTravelConfigUC,
) : CustomViewModel<NewTravelVehicleVM.State, NewTravelVehicleVM.ScreenData, NewTravelVehicleVM.Action.Navigation>(
  initialStateValue = NewTravelVehicleVM.State.Initial,
), NewTravelVehicleVM {
  override val screenData: StateFlow<NewTravelVehicleVM.ScreenData> = _screenData

  init {
    initState()
  }

  override suspend fun onStateEnter(newState: NewTravelVehicleVM.State) {
    when (newState) {
      NewTravelVehicleVM.State.Initial -> {
        getSavedNewTravelConfigUC(UseCase.Params.Empty).fold(
          onRight = { config ->
            NewTravelVehicleVM.State.Initialized(
              newTravelConfig = config,
            ).override()
          },
          onLeft = { error ->
            //todo error handling
          }
        )
      }
      is NewTravelVehicleVM.State.Initialized -> {}
    }
  }

  override fun mapScreenData(): NewTravelVehicleVM.ScreenData = newTravelVehicleScreenMapper(
    params = NewTravelVehicleScreenMapper.Params(
      state = state.value,
      onBackClick = {
        dispatchAction(NewTravelVehicleVM.Action.ShowDialog(dialogType = DialogType.Close))
      },
      onNextClick = {
        dispatchAction(NewTravelVehicleVM.Action.OnNextClick)
      },
      onSelectVehicle = { vehicleId ->
        dispatchAction(NewTravelVehicleVM.Action.UpdateSelectedOriginVehicleType(vehicleId = vehicleId))
      }
    ),
  )

  private fun dispatchAction(action: NewTravelVehicleVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        NewTravelVehicleVM.State.Initial -> when (action) {
          is NewTravelVehicleVM.Action.ShowDialog -> NewTravelVehicleVM.Action.Navigation.ShowDialog(
            dialogData = newTravelVehicleDialogMapper(
              params = NewTravelVehicleDialogMapper.Params(
                type = action.dialogType,
                onCloseClick = { dispatchAction(NewTravelVehicleVM.Action.Back) }
              ),
            ),
          ).emit()
          NewTravelVehicleVM.Action.Back -> NewTravelVehicleVM.Action.Navigation.Back.emit()
          is NewTravelVehicleVM.Action.UpdateSelectedOriginVehicleType -> {}
          is NewTravelVehicleVM.Action.OnNextClick -> {}
        }
        is NewTravelVehicleVM.State.Initialized -> when (action) {
          is NewTravelVehicleVM.Action.ShowDialog -> NewTravelVehicleVM.Action.Navigation.ShowDialog(
            dialogData = newTravelVehicleDialogMapper(
              params = NewTravelVehicleDialogMapper.Params(
                type = action.dialogType,
                onCloseClick = { dispatchAction(NewTravelVehicleVM.Action.Back) }
              ),
            ),
          ).emit()
          NewTravelVehicleVM.Action.Back -> NewTravelVehicleVM.Action.Navigation.Back.emit()
          is NewTravelVehicleVM.Action.OnNextClick -> {
            NewTravelVehicleVM.Action.Navigation.ToOrigin(
              setupData = NewTravelOriginVM.NewTravelSetupData(
                newTravelConfig = currentState.newTravelConfig,
                originVehicleType = currentState.selectedOriginVehicleType,
              ),
            ).emit()
          }
          is NewTravelVehicleVM.Action.UpdateSelectedOriginVehicleType -> {
            currentState.copy(
              selectedOriginVehicleType = VehicleType.entries.find { it.ordinal == action.vehicleId } ?: VehicleType.PLANE,
            ).mutate()
          }
        }
      }
    }
  }

}