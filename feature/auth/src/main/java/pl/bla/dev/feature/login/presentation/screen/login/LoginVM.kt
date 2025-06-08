package pl.bla.dev.feature.login.presentation.screen.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.loader.domain.RunWithLoaderUC
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.input.TextFieldData
import pl.bla.dev.common.ui.componenst.input.ValidationState
import pl.bla.dev.feature.login.domain.usecae.GetSavedUserNameUC
import pl.bla.dev.feature.login.domain.usecae.ValidateUserPasswordUC
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM.Action
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM.ScreenData
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM.State
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapper
import javax.inject.Inject

interface LoginVM {
  sealed interface State {
    data object Initial : State
    data class Login(
      val userName: String,
      val typedPassword: String = "",
      val passwordState: ValidationState = ValidationState.UnVerified,
    ) : State
    data object Registration : State
  }

  sealed interface Action {
    sealed interface Navigation : Action {
      data object ToDashboard : Navigation
      data object ToRegistration : Navigation
      data object Back : Navigation
    }

    data object LoginToApp : Action
    data object ToRegistration : Action
    data class UpdatePassword(val password: String) : Action
  }

  sealed interface ScreenData {
    data class LoginScreen(
      val appName: String,
      val welcomeLabel: String,
      val buttonData: LargeButtonData,
      val textFieldData: TextFieldData,
    ) : ScreenData

    data class RegistrationScreen(
      val appName: String,
      val buttonData: LargeButtonData,
    ) : ScreenData

    data object Initial : ScreenData
  }

  val screenData: StateFlow<ScreenData>
}


@HiltViewModel
class LoginVMImpl @Inject constructor(
  private val loginScreenMapper: LoginScreenMapper,
  private val getSavedUserNameUC: GetSavedUserNameUC,
  private val validateUserPasswordUC: ValidateUserPasswordUC,
  private val runWithLoaderUC: RunWithLoaderUC,
) : CustomViewModel<State, ScreenData, Action.Navigation>(
  initialStateValue = State.Initial,
), LoginVM {
  override val screenData: StateFlow<ScreenData> = _screenData

  init {
    initState()
  }

  fun dispatchAction(action: Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        State.Initial -> {}
        is State.Login -> {
          when (action) {
            is Action.LoginToApp -> {
              runWithLoaderUC {
                validateUserPasswordUC(
                  param = ValidateUserPasswordUC.Params(
                    typedPassword = currentState.typedPassword
                  ),
                ).fold(
                  onRight = { result ->
                    when (result) {
                      ValidateUserPasswordUC.Result.Success -> {
                        dispatchAction(Action.UpdatePassword(password = ""))
                        Action.Navigation.ToDashboard.emit()
                      }
                      ValidateUserPasswordUC.Result.WrongPassword -> {
                        currentState.copy(
                          passwordState = ValidationState.Invalid("Złe hasło!"),
                        ).mutate()
                      }
                    }
                  },
                  onLeft = {
                    //TODO show error dialog
                  }
                )
              }
            }
            is Action.ToRegistration -> {
              Action.Navigation.ToRegistration.emit()
            }
            is Action.UpdatePassword -> {
              currentState.copy(
                typedPassword = action.password,
                passwordState = ValidationState.UnVerified,
              ).mutate()
            }
            else -> {}
          }
        }
        State.Registration -> {}
      }
    }
  }

  override suspend fun onStateEnter(newState: State) {
    when (newState) {
      State.Initial -> {
        getSavedUserNameUC(param = UseCase.Params.Empty).fold(
          onRight = { name ->
            State.Login(
              userName = name,
            ).override()
          },
          onLeft = {
            State.Registration.override()
          }
        )
      }
      is State.Login -> {}
      State.Registration -> {}
    }
  }

  override fun mapScreenData() = loginScreenMapper(
    params = LoginScreenMapper.Params(
      state = state.value,
      onLoginClick = {
        dispatchAction(Action.LoginToApp)
      },
      onRegisterClick = {
        dispatchAction(Action.ToRegistration)
      },
      onPasswordValueChanged = { password ->
        dispatchAction(Action.UpdatePassword(password = password))
      },
      onForgotPasswordClick = {
        //TODO dialog
        State.Registration.override()
      }
    ),
  )

}