package pl.bla.dev.feature.login.presentation.screen.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM.Action
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM.ScreenData
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM.State
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapper
import javax.inject.Inject

interface LoginVM {
  sealed interface State {
    data object Initial : State
  }

  sealed interface Action {
    sealed interface Navigation : Action {
      data object ToDashboard : Navigation
      data object Back : Navigation
    }

    data object LoginToApp : Action
  }

  sealed interface ScreenData {
    data class MainScreen(
      val yes: String,
      val buttonData: LargeButtonData,
    ) : ScreenData

    data object Initial : ScreenData
  }

  val screenData: StateFlow<ScreenData>
}


@HiltViewModel
class LoginVMImpl @Inject constructor(
  private val loginScreenMapper: LoginScreenMapper,
) : CustomViewModel<State, ScreenData, Action.Navigation>(
  initialStateValue = State.Initial,
), LoginVM {
  override val screenData: StateFlow<ScreenData> = _screenData

  fun dispatchAction(action: Action) = viewModelScope.launch {
    when (action) {
      is Action.LoginToApp -> {
        Action.Navigation.ToDashboard.emit()
      }
      else -> {}
    }
  }

  override fun mapScreenData() = loginScreenMapper(
    params = LoginScreenMapper.Params(
      state = state.value,
      onShowDialog = { dialogData ->
        dispatchAction(Action.LoginToApp)
      }
    ),
  )

}