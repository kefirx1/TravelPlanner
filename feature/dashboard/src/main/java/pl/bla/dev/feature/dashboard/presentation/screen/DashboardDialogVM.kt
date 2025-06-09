package pl.bla.dev.feature.dashboard.presentation.screen

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.feature.dashboard.presentation.screen.DashboardDialogVM.DialogSetupData
import pl.bla.dev.feature.dashboard.presentation.screen.DashboardDialogVMImpl.DialogVMFactory
import pl.bla.dev.feature.dashboard.presentation.screen.mapper.DashboardDialogMapper

interface DashboardDialogVM {
  data object State

  sealed interface Action {
    sealed interface Navigation : Action {
      data object OnLogout : Navigation
      data object OnDismiss : Navigation
    }
  }

  data class ScreenData(
    val dialogData: DialogData,
  )

  val screenData: StateFlow<ScreenData>

  data class DialogSetupData(
    val dialogData: DialogData,
  )
}

@HiltViewModel(assistedFactory = DialogVMFactory::class)
class DashboardDialogVMImpl @AssistedInject constructor(
  private val dashboardDialogMapper: DashboardDialogMapper,
  @Assisted val setupData: DialogSetupData,
) : CustomViewModel<DashboardDialogVM.State, DashboardDialogVM.ScreenData, DashboardDialogVM.Action.Navigation>(
  initialStateValue = DashboardDialogVM.State,
), DashboardDialogVM {
  @AssistedFactory
  interface DialogVMFactory: CustomViewModelFactory<DialogSetupData, DashboardDialogVMImpl> {
    override fun setup(setupData: DialogSetupData) : DashboardDialogVMImpl
  }

  override val screenData: StateFlow<DashboardDialogVM.ScreenData> = _screenData

  override suspend fun onStateEnter(newState: DashboardDialogVM.State) = when (newState) {
    DashboardDialogVM.State -> {}
    else -> {}
  }

  override fun mapScreenData(): DashboardDialogVM.ScreenData = dashboardDialogMapper(
    params = DashboardDialogMapper.Params(
      onDismiss = {
        DashboardDialogVM.Action.Navigation.OnDismiss.emit()
      },
      onLogout = {
        DashboardDialogVM.Action.Navigation.OnLogout.emit()
      },
    ),
  )

}