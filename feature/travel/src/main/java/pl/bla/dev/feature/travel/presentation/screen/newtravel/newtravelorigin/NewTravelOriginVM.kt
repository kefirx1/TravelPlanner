package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.NewTravelDestinationVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.NewTravelOriginVM.NewTravelSetupData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.mapper.NewTravelOriginDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.mapper.NewTravelOriginDialogMapper.DialogType
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.mapper.NewTravelOriginScreenMapper

interface NewTravelOriginVM {
  sealed interface State {
    data object Initial : State
  }

  sealed interface Action {
    sealed interface Navigation {
      data object Back : Navigation
      data object CloseProcess : Navigation
      data class ShowDialog(
        val dialogData: DialogData,
      ) : Navigation
      data class ToDestination(
        val setupData: NewTravelDestinationVM.NewTravelSetupData,
      ) : Navigation
    }

    data object OnNextClick : Action
    data class ShowDialog(
      val dialogType: DialogType,
    ) : Action
    data object CloseProcess : Action
    data object Back : Action
  }

  data class ScreenData(
    val topAppBarData: TopAppBarData,
    val nextButtonData: LargeButtonData.Primary,
    val onBackClick: () -> Unit,
  )

  val screenData: StateFlow<ScreenData>

  data class NewTravelSetupData(
    val id: String,
  )
}


@HiltViewModel(assistedFactory = NewTravelOriginVMImpl.NewTravelOriginVMFactory::class)
class NewTravelOriginVMImpl @AssistedInject constructor(
  private val newTravelOriginScreenMapper: NewTravelOriginScreenMapper,
  private val newTravelOriginDialogMapper: NewTravelOriginDialogMapper,
  @Assisted val setupData: NewTravelSetupData,
) : CustomViewModel<NewTravelOriginVM.State, NewTravelOriginVM.ScreenData, NewTravelOriginVM.Action.Navigation>(
  initialStateValue = NewTravelOriginVM.State.Initial,
), NewTravelOriginVM {

  @AssistedFactory
  interface NewTravelOriginVMFactory: CustomViewModelFactory<NewTravelSetupData, NewTravelOriginVMImpl> {
    override fun setup(setupData: NewTravelSetupData): NewTravelOriginVMImpl
  }

  override val screenData: StateFlow<NewTravelOriginVM.ScreenData> = _screenData

  init {
    initState()
  }

  override suspend fun onStateEnter(newState: NewTravelOriginVM.State) {
    when (newState) {
      NewTravelOriginVM.State.Initial -> {

      }
    }
  }

  override fun mapScreenData(): NewTravelOriginVM.ScreenData = newTravelOriginScreenMapper(
    params = NewTravelOriginScreenMapper.Params(
      state = state.value,
      onBackClick = {
        dispatchAction(NewTravelOriginVM.Action.Back)
      },
      onCloseProcessClick = {
        dispatchAction(NewTravelOriginVM.Action.ShowDialog(dialogType = DialogType.Close))
      },
      onNextClick = {
        dispatchAction(NewTravelOriginVM.Action.OnNextClick)
      }
    ),
  )

  private fun dispatchAction(action: NewTravelOriginVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        NewTravelOriginVM.State.Initial -> when (action) {
          is NewTravelOriginVM.Action.ShowDialog -> NewTravelOriginVM.Action.Navigation.ShowDialog(
            dialogData = newTravelOriginDialogMapper(
              params = NewTravelOriginDialogMapper.Params(
                type = action.dialogType,
                onCloseClick = { dispatchAction(NewTravelOriginVM.Action.CloseProcess) }
              ),
            ),
          ).emit()
          NewTravelOriginVM.Action.OnNextClick -> NewTravelOriginVM.Action.Navigation.ToDestination(
            setupData = NewTravelDestinationVM.NewTravelSetupData(id = ""),
          ).emit()
          NewTravelOriginVM.Action.CloseProcess -> NewTravelOriginVM.Action.Navigation.CloseProcess.emit()
          NewTravelOriginVM.Action.Back -> NewTravelOriginVM.Action.Navigation.Back.emit()
        }
      }
    }
  }

}