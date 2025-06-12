package pl.bla.dev.feature.dashboard.presentation.screen.settings.changepassword

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
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.common.validators.ValidationResult
import pl.bla.dev.feature.dashboard.presentation.screen.settings.changepassword.mapper.ChangePasswordScreenMapper
import pl.bla.dev.feature.settings.contract.domain.usecase.ChangeUserPasswordUC
import pl.bla.dev.feature.settings.contract.domain.usecase.ClearUserSessionUC
import pl.bla.dev.feature.settings.contract.domain.usecase.ValidatePasswordUC
import pl.bla.dev.feature.settings.contract.domain.usecase.ValidateRepeatPasswordUC
import javax.inject.Inject

interface ChangePasswordVM {
  sealed class State {
    data class Initialized(
      val typedPassword: String = "",
      val typedRepeatPassword: String = "",
      val passwordState: ValidationState = ValidationState.UnVerified,
      val passwordRepeatState: ValidationState = ValidationState.UnVerified,
    ) : State()
  }

  sealed interface Action {
    sealed interface Navigation {
      data object Back : Navigation
      data object Logout : Navigation
    }

    data class UpdatePassword(val password: String) : Action
    data class UpdateRepeatPassword(val password: String) : Action
    data object ValidatePassword : Action
    data object ValidateRepeatPassword : Action
    data object ChangePassword : Action
    data object Logout : Action
    data object Back : Action
  }

  sealed class ScreenData {
    data class Initialized(
      val screenDescription: String,
      val screenBody: String,
      val tabData: TopAppBarData,
      val passwordInput: TextFieldData,
      val passwordRepeatInput: TextFieldData,
      val changePasswordButtonData: LargeButtonData.Primary,
      val onBackClick: () -> Unit,
    ) : ScreenData()
  }

  val screenData: StateFlow<ScreenData>
}


@HiltViewModel
class ChangePasswordVMImpl @Inject constructor(
  private val changePasswordScreenMapper: ChangePasswordScreenMapper,
  private val runWithLoaderUC: RunWithLoaderUC,
  private val validatePasswordUC: ValidatePasswordUC,
  private val validateRepeatPasswordUC: ValidateRepeatPasswordUC,
  private val changeUserPasswordUC: ChangeUserPasswordUC,
  private val clearUserSessionUC: ClearUserSessionUC,
) : CustomViewModel<ChangePasswordVM.State, ChangePasswordVM.ScreenData, ChangePasswordVM.Action.Navigation>(
  initialStateValue = ChangePasswordVM.State.Initialized(),
), ChangePasswordVM {
  override val screenData: StateFlow<ChangePasswordVM.ScreenData> = _screenData

  init {
    initState()
  }

  override suspend fun onStateEnter(newState: ChangePasswordVM.State) {
    when (newState) {
      is ChangePasswordVM.State.Initialized -> {}
    }
  }

  override fun mapScreenData(): ChangePasswordVM.ScreenData = changePasswordScreenMapper(
    params = ChangePasswordScreenMapper.Params(
      state = state.value,
      onBackClick = {
        dispatchAction(ChangePasswordVM.Action.Back)
      },
      onChangePasswordClick = {
        dispatchAction(ChangePasswordVM.Action.ChangePassword)
      },
      onPasswordValueChanged = { password ->
        dispatchAction(ChangePasswordVM.Action.UpdatePassword(password = password))
      },
      onPasswordFocusChanged = { isFocused ->
        if (!isFocused) {
          dispatchAction(ChangePasswordVM.Action.ValidatePassword)
        }
      },
      onPasswordRepeatValueChanged = { password ->
        dispatchAction(ChangePasswordVM.Action.UpdateRepeatPassword(password = password))
      },
      onPasswordRepeatFocusChanged = { isFocused ->
        if (!isFocused) {
          dispatchAction(ChangePasswordVM.Action.ValidateRepeatPassword)
        }
      },
    )
  )

  private fun dispatchAction(action: ChangePasswordVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        is ChangePasswordVM.State.Initialized -> when (action) {
          is ChangePasswordVM.Action.UpdatePassword -> {
            currentState.copy(
              typedPassword = action.password,
              passwordState = ValidationState.UnVerified,
            ).mutate()
          }
          is ChangePasswordVM.Action.UpdateRepeatPassword -> {
            currentState.copy(
              typedRepeatPassword = action.password,
              passwordRepeatState = ValidationState.UnVerified,
            ).mutate()
          }
          is ChangePasswordVM.Action.ValidatePassword -> {
            currentState.copy(
              passwordState = validatePasswordUC(param = ValidatePasswordUC.Params(
                password = currentState.typedPassword,
              )).getState()
            ).mutate()
          }
          is ChangePasswordVM.Action.ValidateRepeatPassword -> {
            currentState.copy(
              passwordRepeatState = validateRepeatPasswordUC(param = ValidateRepeatPasswordUC.Params(
                password = currentState.typedPassword,
                repeatedPassword = currentState.typedRepeatPassword,
              )).getState()
            ).mutate()
          }
          is ChangePasswordVM.Action.ChangePassword -> {
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
                changeUserPasswordUC(
                  param = ChangeUserPasswordUC.Params(
                    newPassword = currentState.typedPassword,
                  ),
                ).fold(
                  onRight = {
                    dispatchAction(ChangePasswordVM.Action.Logout)
                  },
                  onLeft = {
                    //Error handling
                  }
                )
              }
            }

            currentState.copy(
              passwordState = newPasswordState,
              passwordRepeatState = newRepeatPasswordState,
            ).mutate()
          }
          is ChangePasswordVM.Action.Logout -> {
            clearUserSessionUC(UseCase.Params.Empty)
            ChangePasswordVM.Action.Navigation.Logout.emit()
          }
          is ChangePasswordVM.Action.Back -> ChangePasswordVM.Action.Navigation.Back.emit()
        }
      }
    }
  }

  private fun ValidationResult.getState() = when (this) {
    is ValidationResult.Invalid -> ValidationState.Invalid(message = message)
    ValidationResult.Valid -> ValidationState.Valid
  }
}