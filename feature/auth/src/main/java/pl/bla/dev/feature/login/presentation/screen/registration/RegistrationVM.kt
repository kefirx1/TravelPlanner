package pl.bla.dev.feature.login.presentation.screen.registration

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentSection
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.loader.domain.RunWithLoaderUC
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.input.TextFieldData
import pl.bla.dev.common.ui.componenst.input.ValidationState
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.common.validators.ValidationResult
import pl.bla.dev.feature.login.domain.usecase.AfterLoginActionUC
import pl.bla.dev.feature.login.presentation.screen.registration.RegistrationVM.RegistrationSetupData
import pl.bla.dev.feature.login.presentation.screen.registration.mapper.RegistrationScreenMapper
import pl.bla.dev.feature.settings.contract.domain.usecase.RegisterNewUserUC
import pl.bla.dev.feature.settings.contract.domain.usecase.ValidatePasswordUC
import pl.bla.dev.feature.settings.contract.domain.usecase.ValidateRepeatPasswordUC

interface RegistrationVM {
  data class State(
    val typedPassword: String = "",
    val typedRepeatPassword: String = "",
    val passwordState: ValidationState = ValidationState.UnVerified,
    val passwordRepeatState: ValidationState = ValidationState.UnVerified,
  )

  sealed interface Action {
    sealed interface Navigation : Action {
      data object RegistrationCompleted : Navigation
      data object Back : Navigation
    }

    data object RegisterNewUser : Action
    data class UpdatePassword(val password: String) : Action
    data class UpdateRepeatPassword(val password: String) : Action
    data object ValidatePassword : Action
    data object ValidateRepeatPassword : Action
    data object Back : Action
  }

  data class ScreenData(
    val screenDescription: String,
    val screenBody: String,
    val tabData: TopAppBarData,
    val passwordInput: TextFieldData,
    val passwordRepeatInput: TextFieldData,
    val registerButtonData: LargeButtonData.Primary,
    val onBackClick: () -> Unit
  )

  val screenData: StateFlow<ScreenData>

  data class RegistrationSetupData(
    val selectedChips: List<OnboardingContentSection>,
    val email: String,
    val userName: String,
  )
}

@HiltViewModel(assistedFactory = RegistrationVMImpl.RegistrationVMFactory::class)
class RegistrationVMImpl @AssistedInject constructor(
  private val registrationScreenMapper: RegistrationScreenMapper,
  private val registerNewUserUC: RegisterNewUserUC,
  private val runWithLoaderUC: RunWithLoaderUC,
  private val validatePasswordUC: ValidatePasswordUC,
  private val validateRepeatPasswordUC: ValidateRepeatPasswordUC,
  private val afterLoginActionUC: AfterLoginActionUC,
  @Assisted val setupData: RegistrationSetupData,
) : CustomViewModel<RegistrationVM.State, RegistrationVM.ScreenData, RegistrationVM.Action.Navigation>(
  initialStateValue = RegistrationVM.State(),
), RegistrationVM {

  @AssistedFactory
  interface RegistrationVMFactory: CustomViewModelFactory<RegistrationSetupData, RegistrationVMImpl> {
    override fun setup(setupData: RegistrationSetupData): RegistrationVMImpl
  }

  override val screenData: StateFlow<RegistrationVM.ScreenData> = _screenData

  init {
    initState()
  }

  fun dispatchAction(action: RegistrationVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        is RegistrationVM.State -> when (action) {
          is RegistrationVM.Action.UpdatePassword -> {
            currentState.copy(
              typedPassword = action.password,
              passwordState = ValidationState.UnVerified,
            ).mutate()
          }
          is RegistrationVM.Action.UpdateRepeatPassword -> {
            currentState.copy(
              typedRepeatPassword = action.password,
              passwordRepeatState = ValidationState.UnVerified,
            ).mutate()
          }
          is RegistrationVM.Action.ValidatePassword -> {
            currentState.copy(
              passwordState = validatePasswordUC(param = ValidatePasswordUC.Params(
                password = currentState.typedPassword,
              )).getState()
            ).mutate()
          }
          is RegistrationVM.Action.ValidateRepeatPassword -> {
            currentState.copy(
              passwordRepeatState = validateRepeatPasswordUC(param = ValidateRepeatPasswordUC.Params(
                password = currentState.typedPassword,
                repeatedPassword = currentState.typedRepeatPassword,
              )).getState()
            ).mutate()
          }
          is RegistrationVM.Action.RegisterNewUser -> {
            val newPasswordState = validatePasswordUC(param = ValidatePasswordUC.Params(
              password = currentState.typedPassword,
            )).getState()
            val newRepeatPasswordState = validateRepeatPasswordUC(param = ValidateRepeatPasswordUC.Params(
              password = currentState.typedPassword,
              repeatedPassword = currentState.typedRepeatPassword,
            )).getState()

            if (
              listOf(
                newPasswordState,
                newRepeatPasswordState,
              ).all { state -> ValidationState.Valid == state }
            ) {
              runWithLoaderUC {
                registerNewUserUC(
                  param = RegisterNewUserUC.Params(
                    userPassword = currentState.typedPassword,
                    selectedChips = setupData.selectedChips,
                    userEmail = setupData.email,
                    userName = setupData.userName,
                  ),
                ).fold(
                  onRight = {
                    afterLoginActionUC(UseCase.Params.Empty)
                    RegistrationVM.Action.Navigation.RegistrationCompleted.emit()
                  },
                  onLeft = {
                    //todo error
                  }
                )
              }
            }

            currentState.copy(
              passwordState = newPasswordState,
              passwordRepeatState = newRepeatPasswordState,
            ).mutate()
          }
          is RegistrationVM.Action.Back -> RegistrationVM.Action.Navigation.Back.emit()
          else -> {}
        }
      }
    }
  }

  override suspend fun onStateEnter(newState: RegistrationVM.State) {}

  override fun mapScreenData(): RegistrationVM.ScreenData = registrationScreenMapper(
    params = RegistrationScreenMapper.Params(
      state = state.value,
      onBackClick = { dispatchAction(RegistrationVM.Action.Back) },
      onRegisterClick = {
        dispatchAction(RegistrationVM.Action.RegisterNewUser)
      },
      onPasswordValueChanged = { password ->
        dispatchAction(RegistrationVM.Action.UpdatePassword(password = password))
      },
      onPasswordFocusChanged = { isFocused ->
        if (!isFocused) {
          dispatchAction(RegistrationVM.Action.ValidatePassword)
        }
      },
      onPasswordRepeatValueChanged = { password ->
        dispatchAction(RegistrationVM.Action.UpdateRepeatPassword(password = password))
      },
      onPasswordRepeatFocusChanged = { isFocused ->
        if (!isFocused) {
          dispatchAction(RegistrationVM.Action.ValidateRepeatPassword)
        }
      },
    )
  )

  private fun ValidationResult.getState() = when (this) {
    is ValidationResult.Invalid -> ValidationState.Invalid(message = message)
    ValidationResult.Valid -> ValidationState.Valid
  }
}