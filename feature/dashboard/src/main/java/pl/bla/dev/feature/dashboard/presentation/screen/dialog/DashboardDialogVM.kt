package pl.bla.dev.feature.dashboard.presentation.screen.dialog

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.feature.dashboard.presentation.screen.dialog.DashboardDialogVMImpl.DialogVMFactory

interface DashboardDialogVM {
  data object State

  sealed interface Action {
    sealed interface Navigation : Action {
      data object OnDialogAction : Navigation
    }
  }

  data class ScreenData(
    val dialogData: DialogData,
  )

  val screenData: StateFlow<ScreenData>
}

@HiltViewModel(assistedFactory = DialogVMFactory::class)
class DashboardDialogVMImpl @AssistedInject constructor(
  @Assisted val setupData: DialogData,
) : CustomViewModel<DashboardDialogVM.State, DashboardDialogVM.ScreenData, DashboardDialogVM.Action.Navigation>(
  initialStateValue = DashboardDialogVM.State,
), DashboardDialogVM {
  @AssistedFactory
  interface DialogVMFactory: CustomViewModelFactory<DialogData, DashboardDialogVMImpl> {
    override fun setup(setupData: DialogData) : DashboardDialogVMImpl
  }

  override val screenData: StateFlow<DashboardDialogVM.ScreenData> = _screenData

  override suspend fun onStateEnter(newState: DashboardDialogVM.State) {}

  override fun mapScreenData(): DashboardDialogVM.ScreenData =
    DashboardDialogVM.ScreenData(
      dialogData = setupData.copy(
        onDismiss = {
          DashboardDialogVM.Action.Navigation.OnDialogAction.emit()
          setupData.onDismiss()
        },
        onPrimaryButtonData = setupData.onPrimaryButtonData.copy(
          text = setupData.onPrimaryButtonData.text,
          onClick = {
            DashboardDialogVM.Action.Navigation.OnDialogAction.emit()
            setupData.onPrimaryButtonData.onClick()
          }
        ),
        onSecondaryButtonData = setupData.onSecondaryButtonData?.copy(
          text = setupData.onSecondaryButtonData!!.text,
          onClick = {
            DashboardDialogVM.Action.Navigation.OnDialogAction.emit()
            setupData.onSecondaryButtonData!!.onClick()
          }
        ),
      )
    )
}