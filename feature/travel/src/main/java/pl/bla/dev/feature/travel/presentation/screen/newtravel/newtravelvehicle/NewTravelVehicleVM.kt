package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.NewTravelOriginVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper.NewTravelVehicleDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper.NewTravelVehicleDialogMapper.DialogType
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper.NewTravelVehicleScreenMapper
import javax.inject.Inject

interface NewTravelVehicleVM {
  sealed interface State {
    data object Initial : State
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

    data object OnNextClick : Action
    data class ShowDialog(
      val dialogType: DialogType,
    ) : Action
    data object Back : Action
  }

  data class ScreenData(
    val topAppBarData: TopAppBarData,
    val nextButtonData: LargeButtonData.Primary,
    val onBackClick: () -> Unit,
  )

  val screenData: StateFlow<ScreenData>
}


@HiltViewModel
class NewTravelVehicleVMImpl @Inject constructor(
  private val newTravelVehicleScreenMapper: NewTravelVehicleScreenMapper,
  private val newTravelVehicleDialogMapper: NewTravelVehicleDialogMapper,
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

      }
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
          NewTravelVehicleVM.Action.OnNextClick -> NewTravelVehicleVM.Action.Navigation.ToOrigin(
            setupData = NewTravelOriginVM.NewTravelSetupData(id = ""),
          ).emit()
          NewTravelVehicleVM.Action.Back -> NewTravelVehicleVM.Action.Navigation.Back.emit()
        }
      }
    }
  }

}