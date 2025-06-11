package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate

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
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.NewTravelDateVM.NewTravelSetupData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.mapper.NewTravelDateDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.mapper.NewTravelDateDialogMapper.DialogType
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.mapper.NewTravelDateScreenMapper

interface NewTravelDateVM {
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
    }

    data class ShowDialog(
      val dialogType: DialogType,
    ) : Action
    data object CloseProcess : Action
    data object Back : Action
  }

  data class ScreenData(
    val topAppBarData: TopAppBarData,
    val onBackClick: () -> Unit,
  )

  val screenData: StateFlow<ScreenData>

  data class NewTravelSetupData(
    val newTravelConfig: NewTravelConfig,
  )
}


@HiltViewModel(assistedFactory = NewTravelDateVMImpl.NewTravelDateVMFactory::class)
class NewTravelDateVMImpl @AssistedInject constructor(
  private val newTravelDateScreenMapper: NewTravelDateScreenMapper,
  private val newTravelDateDialogMapper: NewTravelDateDialogMapper,
  @Assisted val setupData: NewTravelSetupData,
) : CustomViewModel<NewTravelDateVM.State, NewTravelDateVM.ScreenData, NewTravelDateVM.Action.Navigation>(
  initialStateValue = NewTravelDateVM.State.Initial,
), NewTravelDateVM {

  @AssistedFactory
  interface NewTravelDateVMFactory: CustomViewModelFactory<NewTravelSetupData, NewTravelDateVMImpl> {
    override fun setup(setupData: NewTravelSetupData): NewTravelDateVMImpl
  }

  override val screenData: StateFlow<NewTravelDateVM.ScreenData> = _screenData

  init {
    initState()
  }

  override suspend fun onStateEnter(newState: NewTravelDateVM.State) {
    when (newState) {
      NewTravelDateVM.State.Initial -> {

      }
    }
  }

  override fun mapScreenData(): NewTravelDateVM.ScreenData = newTravelDateScreenMapper(
    params = NewTravelDateScreenMapper.Params(
      state = state.value,
      onBackClick = {
        dispatchAction(NewTravelDateVM.Action.Back)
      },
      onCloseProcessClick = {
        dispatchAction(NewTravelDateVM.Action.ShowDialog(dialogType = DialogType.Close))
      }
    ),
  )

  private fun dispatchAction(action: NewTravelDateVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        NewTravelDateVM.State.Initial -> when (action) {
          is NewTravelDateVM.Action.ShowDialog -> NewTravelDateVM.Action.Navigation.ShowDialog(
            dialogData = newTravelDateDialogMapper(
              params = NewTravelDateDialogMapper.Params(
                type = action.dialogType,
                onCloseClick = { dispatchAction(NewTravelDateVM.Action.CloseProcess) }
              ),
            ),
          ).emit()
          NewTravelDateVM.Action.CloseProcess -> NewTravelDateVM.Action.Navigation.CloseProcess.emit()
          NewTravelDateVM.Action.Back -> NewTravelDateVM.Action.Navigation.Back.emit()
        }
      }
    }
  }

}