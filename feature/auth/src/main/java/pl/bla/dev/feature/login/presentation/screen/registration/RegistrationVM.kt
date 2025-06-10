package pl.bla.dev.feature.login.presentation.screen.registration

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentSection
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.loader.domain.RunWithLoaderUC
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.input.TextFieldData
import pl.bla.dev.common.ui.componenst.input.ValidationState
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.login.presentation.screen.registration.RegistrationVM.RegistrationSetupData
import pl.bla.dev.feature.login.presentation.screen.registration.mapper.RegistrationScreenMapper
import pl.bla.dev.feature.settings.contract.domain.usecase.RegisterNewUserUC

interface RegistrationVM {
  data class State(
    val typedName: String = "",
    val typedPassword: String = "",
    val typedRepeatPassword: String = "",
    val typedEmail: String = "",
    val passwordState: ValidationState = ValidationState.UnVerified,
    val passwordRepeatState: ValidationState = ValidationState.UnVerified,
    val emailState: ValidationState = ValidationState.UnVerified,
    val typedNameState: ValidationState = ValidationState.UnVerified,
  )

  sealed interface Action {
    sealed interface Navigation : Action {
      data object RegistrationCompleted : Navigation
      data object Back : Navigation
    }

    data object RegisterNewUser : Action
    data class UpdateName(val name: String) : Action
    data class UpdatePassword(val password: String) : Action
    data class UpdateRepeatPassword(val password: String) : Action
    data class UpdateEmail(val email: String) : Action
    data object Back : Action
  }

  data class ScreenData(
    val screenDescription: String,
    val tabData: TopAppBarData,
    val nameInput: TextFieldData,
    val emailInput: TextFieldData,
    val passwordInput: TextFieldData,
    val passwordRepeatInput: TextFieldData,
    val registerButtonData: LargeButtonData.Primary,
    val onBackClick: () -> Unit
  )

  val screenData: StateFlow<ScreenData>

  data class RegistrationSetupData(
    val selectedChips: List<OnboardingContentSection>,
  )
}

@HiltViewModel(assistedFactory = RegistrationVMImpl.RegistrationVMFactory::class)
class RegistrationVMImpl @AssistedInject constructor(
  private val registrationScreenMapper: RegistrationScreenMapper,
  private val registerNewUserUC: RegisterNewUserUC,
  private val runWithLoaderUC: RunWithLoaderUC,
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
          is RegistrationVM.Action.UpdateEmail -> {
            currentState.copy(
              typedEmail = action.email,
              emailState = ValidationState.UnVerified,
            ).mutate()
          }
          is RegistrationVM.Action.UpdateName -> {
            currentState.copy(
              typedName = action.name,
              typedNameState = ValidationState.UnVerified,
            ).mutate()
          }
          is RegistrationVM.Action.RegisterNewUser -> {
            if (true) { //check validators
              runWithLoaderUC {
                registerNewUserUC(
                  param = RegisterNewUserUC.Params(
                    userPassword = currentState.typedPassword,
                    userName = currentState.typedName,
                    userEmail = currentState.typedEmail,
                    selectedChips = setupData.selectedChips,
                  ),
                ).fold(
                  onRight = {
                    RegistrationVM.Action.Navigation.RegistrationCompleted.emit()
                  },
                  onLeft = {
                    //todo error
                  }
                )
              }
            } else {

            }
          }
          is RegistrationVM.Action.Back -> RegistrationVM.Action.Navigation.Back.emit()
          else -> {}
        }
      }
    }
  }

  override suspend fun onStateEnter(newState: RegistrationVM.State) {
    when (newState) {
      is RegistrationVM.State -> {
        println(setupData)
      }
    }
  }

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
      onPasswordRepeatValueChanged = { password ->
        dispatchAction(RegistrationVM.Action.UpdateRepeatPassword(password = password))
      },
      onEmailValueChanged = { email ->
        dispatchAction(RegistrationVM.Action.UpdateEmail(email = email))
      },
      onNameValueChanged = { name ->
        dispatchAction(RegistrationVM.Action.UpdateName(name = name))
      },
    )
  )

}