package pl.bla.dev.feature.login.presentation.screen.personalinfo

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentSection
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.input.TextFieldData
import pl.bla.dev.common.ui.componenst.input.ValidationState
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.common.validators.ValidationResult
import pl.bla.dev.feature.login.domain.usecase.ValidateEmailUC
import pl.bla.dev.feature.login.domain.usecase.ValidateUserNameUC
import pl.bla.dev.feature.login.presentation.screen.personalinfo.PersonalInfoVM.PersonalInfoSetupData
import pl.bla.dev.feature.login.presentation.screen.personalinfo.mapper.PersonalInfoScreenMapper

interface PersonalInfoVM {
  data class State(
    val typedName: String = "",
    val typedEmail: String = "",
    val emailState: ValidationState = ValidationState.UnVerified,
    val typedNameState: ValidationState = ValidationState.UnVerified,
  )

  sealed interface Action {
    sealed interface Navigation : Action {
      data class ToRegistration(
        val selectedChips: List<OnboardingContentSection>,
        val email: String,
        val userName: String,
      ) : Navigation
      data object Back : Navigation
    }

    data object GoToRegistration : Action
    data class UpdateName(val name: String) : Action
    data class UpdateEmail(val email: String) : Action
    data object ValidateName : Action
    data object ValidateEmail : Action
    data object Back : Action
  }

  data class ScreenData(
    val screenDescription: String,
    val tabData: TopAppBarData,
    val nameInput: TextFieldData,
    val emailInput: TextFieldData,
    val nextButtonData: LargeButtonData.Primary,
    val onBackClick: () -> Unit
  )

  val screenData: StateFlow<ScreenData>

  data class PersonalInfoSetupData(
    val selectedChips: List<OnboardingContentSection>,
  )
}

@HiltViewModel(assistedFactory = PersonalInfoVMImpl.PersonalInfoVMFactory::class)
class PersonalInfoVMImpl @AssistedInject constructor(
  private val validateEmailUC: ValidateEmailUC,
  private val validateUserNameUC: ValidateUserNameUC,
  private val personalInfoScreenMapper: PersonalInfoScreenMapper,
  @Assisted val setupData: PersonalInfoSetupData,
) : CustomViewModel<PersonalInfoVM.State, PersonalInfoVM.ScreenData, PersonalInfoVM.Action.Navigation>(
  initialStateValue = PersonalInfoVM.State(),
), PersonalInfoVM {

  @AssistedFactory
  interface PersonalInfoVMFactory: CustomViewModelFactory<PersonalInfoSetupData, PersonalInfoVMImpl> {
    override fun setup(setupData: PersonalInfoSetupData): PersonalInfoVMImpl
  }

  override val screenData: StateFlow<PersonalInfoVM.ScreenData> = _screenData

  init {
    initState()
  }

  fun dispatchAction(action: PersonalInfoVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        is PersonalInfoVM.State -> when (action) {
          is PersonalInfoVM.Action.UpdateEmail -> {
            currentState.copy(
              typedEmail = action.email,
              emailState = ValidationState.UnVerified,
            ).mutate()
          }
          is PersonalInfoVM.Action.UpdateName -> {
            currentState.copy(
              typedName = action.name,
              typedNameState = ValidationState.UnVerified,
            ).mutate()
          }
          is PersonalInfoVM.Action.ValidateName -> {
            currentState.copy(
              typedNameState = validateUserNameUC(param = ValidateUserNameUC.Params(
                userName = currentState.typedName,
              )).getState()
            ).mutate()
          }
          is PersonalInfoVM.Action.ValidateEmail -> {
            currentState.copy(
              emailState = validateEmailUC(param = ValidateEmailUC.Params(
                email = currentState.typedEmail,
              )).getState()
            ).mutate()
          }
          is PersonalInfoVM.Action.GoToRegistration -> {
            val newUserNameState = validateUserNameUC(param = ValidateUserNameUC.Params(
              userName = currentState.typedName,
            )).getState()
            val newEmailState = validateEmailUC(param = ValidateEmailUC.Params(
              email = currentState.typedEmail,
            )).getState()

            if (listOf(newUserNameState, newEmailState).all { state -> ValidationState.Valid == state }) {
              PersonalInfoVM.Action.Navigation.ToRegistration(
                selectedChips = setupData.selectedChips,
                email = currentState.typedEmail,
                userName = currentState.typedName,
              ).emit()
            }

            currentState.copy(
              emailState = newEmailState,
              typedNameState = newUserNameState,
            ).mutate()
          }
          is PersonalInfoVM.Action.Back -> PersonalInfoVM.Action.Navigation.Back.emit()
          else -> {}
        }
      }
    }
  }

  override suspend fun onStateEnter(newState: PersonalInfoVM.State) {}

  override fun mapScreenData(): PersonalInfoVM.ScreenData = personalInfoScreenMapper(
    params = PersonalInfoScreenMapper.Params(
      state = state.value,
      onBackClick = { dispatchAction(PersonalInfoVM.Action.Back) },
      onNextClick = {
        dispatchAction(PersonalInfoVM.Action.GoToRegistration)
      },
      onEmailValueChanged = { email ->
        dispatchAction(PersonalInfoVM.Action.UpdateEmail(email = email))
      },
      onEmailFocusChanged = { isFocused ->
        if (!isFocused) {
          dispatchAction(PersonalInfoVM.Action.ValidateEmail)
        }
      },
      onNameValueChanged = { name ->
        dispatchAction(PersonalInfoVM.Action.UpdateName(name = name))
      },
      onNameFocusChanged = { isFocused ->
        if (!isFocused) {
          dispatchAction(PersonalInfoVM.Action.ValidateName)
        }
      }
    )
  )

  private fun ValidationResult.getState() = when (this) {
    is ValidationResult.Invalid -> ValidationState.Invalid(message = message)
    ValidationResult.Valid -> ValidationState.Valid
  }
}