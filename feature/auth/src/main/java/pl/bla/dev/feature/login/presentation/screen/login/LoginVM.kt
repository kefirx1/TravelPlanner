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
import pl.bla.dev.common.ui.componenst.button.SmallButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.common.ui.componenst.input.TextFieldData
import pl.bla.dev.common.ui.componenst.input.ValidationState
import pl.bla.dev.feature.login.domain.usecase.AfterLoginActionUC
import pl.bla.dev.feature.login.domain.usecase.ValidateUserContainerPasswordUC
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM.Action
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM.ScreenData
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM.State
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenDialogMapper
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapper
import pl.bla.dev.feature.settings.contract.domain.usecase.DecryptUserDEKByBiometricAndInjectCacheUC
import pl.bla.dev.feature.settings.contract.domain.usecase.GetSavedUserNameUC
import pl.bla.dev.feature.settings.contract.domain.usecase.IsBiometricEnabledUC
import javax.inject.Inject

interface LoginVM {
  sealed interface State {
    data object Initial : State
    data class Login(
      val fromBiometric: Boolean = false,
      val userName: String,
      val typedPassword: String = "",
      val passwordState: ValidationState = ValidationState.UnVerified,
    ) : State
    data class Biometric(
      val userName: String,
    ) : State
    data object Registration : State
  }

  sealed interface Action {
    sealed interface Navigation : Action {
      data object ToDashboard : Navigation
      data object ToOnboarding : Navigation
      data class ShowDialog(
        val dialogData: DialogData,
      ) : Navigation
      data object Back : Navigation
    }

    data object ToBiometricLoginScreen : Action
    data object ToPasswordLoginScreen : Action
    data object ShowBiometricPrompt : Action
    data object SuccessLogin : Action
    data class ShowDialog(val dialogType: LoginScreenDialogMapper.LoginDialogType) : Action
    data object Back : Action
    data object CheckPassword : Action
    data object ToOnboarding : Action
    data class UpdatePassword(val password: String) : Action
  }

  sealed interface ScreenData {
    data class LoginScreen(
      val appName: String,
      val welcomeLabel: String,
      val buttonData: LargeButtonData,
      val textFieldData: TextFieldData,
      val onBackClick: () -> Unit,
      val biometricLoginButtonData: SmallButtonData.Tertiary?,
    ) : ScreenData

    data class BiometricScreen(
      val appName: String,
      val welcomeLabel: String,
      val loginBiometricLabel: String,
      val passwordLoginButtonData: SmallButtonData.Tertiary,
      val onBiometricOpenClick: () -> Unit,
      val onBackClick: () -> Unit,
    ) : ScreenData

    data class RegistrationScreen(
      val appName: String,
      val appDescriptionLabel: String,
      val appDescriptionBody: String,
      val buttonData: LargeButtonData,
      val onBackClick: () -> Unit,
    ) : ScreenData

    data class Initial(
      val onBackClick: () -> Unit,
    ) : ScreenData
  }

  val screenData: StateFlow<ScreenData>
}


@HiltViewModel
class LoginVMImpl @Inject constructor(
  private val loginScreenMapper: LoginScreenMapper,
  private val getSavedUserNameUC: GetSavedUserNameUC,
  private val validateUserPasswordUC: ValidateUserContainerPasswordUC,
  private val runWithLoaderUC: RunWithLoaderUC,
  private val loginScreenDialogMapper: LoginScreenDialogMapper,
  private val afterLoginActionUC: AfterLoginActionUC,
  private val isBiometricEnabledUC: IsBiometricEnabledUC,
  private val decryptUSerDEKByBiometricAndInjectCacheUC: DecryptUserDEKByBiometricAndInjectCacheUC,
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
        State.Initial -> when (action) {
          is Action.Back -> Action.Navigation.Back.emit()
          else ->  {}
        }
        is State.Biometric -> when (action) {
          is Action.ToPasswordLoginScreen -> {
            State.Login(
              fromBiometric = true,
              userName = currentState.userName,
            ).override()
          }
          is Action.SuccessLogin -> {
            afterLoginActionUC(UseCase.Params.Empty)
            Action.Navigation.ToDashboard.emit()
          }
          is Action.ShowBiometricPrompt -> {
            decryptUSerDEKByBiometricAndInjectCacheUC(
              param = UseCase.Params.Empty,
            ).fold(
              onRight = {
                dispatchAction(Action.SuccessLogin)
              },
              onLeft = { error ->
                //TODO error handling
              }
            )
          }
          is Action.Back -> Action.Navigation.Back.emit()
          else -> {}
        }
        is State.Login -> {
          when (action) {
            is Action.ToBiometricLoginScreen -> {
              State.Biometric(
                userName = currentState.userName,
              ).override()
            }
            is Action.ShowDialog -> {
              Action.Navigation.ShowDialog(
                dialogData = loginScreenDialogMapper(
                  params = LoginScreenDialogMapper.Params(
                    dialogType = action.dialogType,
                    onResetClick = {
                      State.Registration.override()
                    },
                  )
                ),
              ).emit()
            }
            is Action.SuccessLogin -> {
              afterLoginActionUC(UseCase.Params.Empty)

              dispatchAction(Action.UpdatePassword(password = ""))
              Action.Navigation.ToDashboard.emit()
            }
            is Action.CheckPassword -> {
              runWithLoaderUC {
                validateUserPasswordUC(
                  param = ValidateUserContainerPasswordUC.Params(
                    typedPassword = currentState.typedPassword
                  ),
                ).fold(
                  onRight = { result ->
                    when (result) {
                      ValidateUserContainerPasswordUC.Result.Success ->
                        dispatchAction(Action.SuccessLogin)
                      ValidateUserContainerPasswordUC.Result.WrongPassword -> {
                        currentState.copy(
                          passwordState = ValidationState.Invalid("Złe hasło"),
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
            is Action.UpdatePassword -> {
              currentState.copy(
                typedPassword = action.password,
                passwordState = ValidationState.UnVerified,
              ).mutate()
            }
            is Action.Back -> Action.Navigation.Back.emit()
            else -> {}
          }
        }
        State.Registration -> when (action) {
          is Action.Back -> Action.Navigation.Back.emit()
          is Action.ToOnboarding -> {
            Action.Navigation.ToOnboarding.emit()
          }
          else -> {}
        }
      }
    }
  }

  override suspend fun onStateEnter(newState: State) {
    when (newState) {
      State.Initial -> {
        getSavedUserNameUC(param = UseCase.Params.Empty).fold(
          onRight = { name ->
            isBiometricEnabledUC(param = UseCase.Params.Empty).fold(
              onRight = { isBiometricEnabled ->
                if (isBiometricEnabled) {
                  State.Biometric(
                    userName = name,
                  ).override()
                } else {
                  State.Login(
                    userName = name,
                  ).override()
                }
              },
              onLeft = { error ->
                //TODO error handling
                State.Login(
                  userName = name,
                ).override()
              }
            )
          },
          onLeft = { error ->
            State.Registration.override()
          }
        )
      }
      is State.Biometric -> {
        dispatchAction(Action.ShowBiometricPrompt)
      }
      is State.Login -> {}
      State.Registration -> {}
    }
  }

  override fun mapScreenData() = loginScreenMapper(
    params = LoginScreenMapper.Params(
      state = state.value,
      onLoginClick = {
        dispatchAction(Action.CheckPassword)
      },
      onStartClick = {
        dispatchAction(Action.ToOnboarding)
      },
      onPasswordValueChanged = { password ->
        dispatchAction(Action.UpdatePassword(password = password))
      },
      onForgotPasswordClick = {
        dispatchAction(Action.ShowDialog(dialogType = LoginScreenDialogMapper.LoginDialogType.ForgotPassword))
      },
      onBackClick = {
        dispatchAction(Action.Back)
      },
      onBiometricOpenClick = {
        dispatchAction(Action.ShowBiometricPrompt)
      },
      onToPasswordLoginClick = {
        dispatchAction(Action.ToPasswordLoginScreen)
      },
      onToBiometricLoginClick = {
        dispatchAction(Action.ToBiometricLoginScreen)
      }
    ),
  )

}