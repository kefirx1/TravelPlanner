package pl.bla.dev.feature.travel.presentation.screen.dialog

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.feature.travel.presentation.screen.dialog.TravelDialogVMImpl.DialogVMFactory

interface TravelDialogVM {
  data object State

  sealed interface Action {
    sealed interface Navigation : Action {
      data class OnDialogAction(
        val dailogAction: () -> Unit,
      ) : Navigation
    }
  }

  data class ScreenData(
    val dialogData: DialogData,
  )

  val screenData: StateFlow<ScreenData>
}

@HiltViewModel(assistedFactory = DialogVMFactory::class)
class TravelDialogVMImpl @AssistedInject constructor(
  @Assisted val setupData: DialogData,
) : CustomViewModel<TravelDialogVM.State, TravelDialogVM.ScreenData, TravelDialogVM.Action.Navigation>(
  initialStateValue = TravelDialogVM.State,
), TravelDialogVM {
  @AssistedFactory
  interface DialogVMFactory: CustomViewModelFactory<DialogData, TravelDialogVMImpl> {
    override fun setup(setupData: DialogData) : TravelDialogVMImpl
  }

  override val screenData: StateFlow<TravelDialogVM.ScreenData> = _screenData

  override suspend fun onStateEnter(newState: TravelDialogVM.State) {}

  override fun mapScreenData(): TravelDialogVM.ScreenData =
    TravelDialogVM.ScreenData(
      dialogData = setupData.copy(
        onDismiss = {
          TravelDialogVM.Action.Navigation.OnDialogAction(
            dailogAction = setupData.onDismiss
          ).emit()
        },
        onPrimaryButtonData = setupData.onPrimaryButtonData.copy(
          text = setupData.onPrimaryButtonData.text,
          onClick = {
            TravelDialogVM.Action.Navigation.OnDialogAction(
              dailogAction = setupData.onPrimaryButtonData.onClick
            ).emit()
          }
        ),
        onSecondaryButtonData = setupData.onSecondaryButtonData?.copy(
          text = setupData.onSecondaryButtonData!!.text,
          onClick = {
            TravelDialogVM.Action.Navigation.OnDialogAction(
              dailogAction = setupData.onSecondaryButtonData!!.onClick,
            ).emit()
          }
        ),
      )
    )
}