package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.NewTravelDateVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.NewTravelDestinationVM.NewTravelSetupData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.mapper.NewTravelDestinationDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.mapper.NewTravelDestinationDialogMapper.DialogType
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.mapper.NewTravelDestinationScreenMapper

interface NewTravelDestinationVM {
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
      data class ToDate(
        val setupData: NewTravelDateVM.NewTravelSetupData,
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
    val newTravelConfig: NewTravelConfig,
  )
}


@HiltViewModel(assistedFactory = NewTravelDestinationVMImpl.NewTravelDestinationVMFactory::class)
class NewTravelDestinationVMImpl @AssistedInject constructor(
  private val newTravelDestinationScreenMapper: NewTravelDestinationScreenMapper,
  private val newTravelDestinationDialogMapper: NewTravelDestinationDialogMapper,
  @Assisted val setupData: NewTravelSetupData,
) : CustomViewModel<NewTravelDestinationVM.State, NewTravelDestinationVM.ScreenData, NewTravelDestinationVM.Action.Navigation>(
  initialStateValue = NewTravelDestinationVM.State.Initial,
), NewTravelDestinationVM {

  @AssistedFactory
  interface NewTravelDestinationVMFactory: CustomViewModelFactory<NewTravelSetupData, NewTravelDestinationVMImpl> {
    override fun setup(setupData: NewTravelSetupData): NewTravelDestinationVMImpl
  }

  override val screenData: StateFlow<NewTravelDestinationVM.ScreenData> = _screenData

  init {
    initState()
  }

  override suspend fun onStateEnter(newState: NewTravelDestinationVM.State) {
    when (newState) {
      NewTravelDestinationVM.State.Initial -> {

      }
    }
  }

  override fun mapScreenData(): NewTravelDestinationVM.ScreenData = newTravelDestinationScreenMapper(
    params = NewTravelDestinationScreenMapper.Params(
      state = state.value,
      onBackClick = {
        dispatchAction(NewTravelDestinationVM.Action.Back)
      },
      onCloseProcessClick = {
        dispatchAction(NewTravelDestinationVM.Action.ShowDialog(dialogType = DialogType.Close))
      },
      onNextClick = {
        dispatchAction(NewTravelDestinationVM.Action.OnNextClick)
      }
    ),
  )

  private fun dispatchAction(action: NewTravelDestinationVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        NewTravelDestinationVM.State.Initial -> when (action) {
          is NewTravelDestinationVM.Action.ShowDialog -> NewTravelDestinationVM.Action.Navigation.ShowDialog(
            dialogData = newTravelDestinationDialogMapper(
              params = NewTravelDestinationDialogMapper.Params(
                type = action.dialogType,
                onCloseClick = { dispatchAction(NewTravelDestinationVM.Action.CloseProcess) }
              ),
            ),
          ).emit()
          NewTravelDestinationVM.Action.OnNextClick -> NewTravelDestinationVM.Action.Navigation.ToDate(
            setupData = NewTravelDateVM.NewTravelSetupData(newTravelConfig = setupData.newTravelConfig),
          ).emit()
          NewTravelDestinationVM.Action.CloseProcess -> NewTravelDestinationVM.Action.Navigation.CloseProcess.emit()
          NewTravelDestinationVM.Action.Back -> NewTravelDestinationVM.Action.Navigation.Back.emit()
        }
      }
    }
  }

}