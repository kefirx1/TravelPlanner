package pl.bla.dev.feature.login.presentation.screen.dialog

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.feature.login.presentation.screen.dialog.AuthDialogVMImpl.DialogVMFactory

interface AuthDialogVM {
  data object State

  sealed interface Action {
    sealed interface Navigation : Action {
      data class OnDialogAction(
        val dialogAction: () -> Unit,
      ) : Navigation
    }
  }

  data class ScreenData(
    val dialogData: DialogData,
  )

  val screenData: StateFlow<ScreenData>
}

@HiltViewModel(assistedFactory = DialogVMFactory::class)
class AuthDialogVMImpl @AssistedInject constructor(
  @Assisted val setupData: DialogData,
) : CustomViewModel<AuthDialogVM.State, AuthDialogVM.ScreenData, AuthDialogVM.Action.Navigation>(
  initialStateValue = AuthDialogVM.State,
), AuthDialogVM {
  @AssistedFactory
  interface DialogVMFactory: CustomViewModelFactory<DialogData, AuthDialogVMImpl> {
    override fun setup(setupData: DialogData) : AuthDialogVMImpl
  }

  override val screenData: StateFlow<AuthDialogVM.ScreenData> = _screenData

  override suspend fun onStateEnter(newState: AuthDialogVM.State) {}

  override fun mapScreenData(): AuthDialogVM.ScreenData =
    AuthDialogVM.ScreenData(
      dialogData = setupData.copy(
        onDismiss = {
          AuthDialogVM.Action.Navigation.OnDialogAction(
            dialogAction = setupData.onDismiss
          ).emit()
        },
        onPrimaryButtonData = setupData.onPrimaryButtonData.copy(
          text = setupData.onPrimaryButtonData.text,
          onClick = {
            AuthDialogVM.Action.Navigation.OnDialogAction(
              dialogAction = setupData.onPrimaryButtonData.onClick,
            ).emit()
          }
        ),
        onSecondaryButtonData = setupData.onSecondaryButtonData?.copy(
          text = setupData.onSecondaryButtonData!!.text,
          onClick = {
            AuthDialogVM.Action.Navigation.OnDialogAction(
              dialogAction = setupData.onSecondaryButtonData!!.onClick,
            ).emit()
          }
        ),
      )
    )
}