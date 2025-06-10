package pl.bla.dev.feature.login.presentation.screen.registration.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.input.TextFieldData
import pl.bla.dev.common.ui.componenst.input.TextFieldType
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.login.presentation.screen.registration.RegistrationVM
import pl.bla.dev.feature.login.presentation.screen.registration.mapper.RegistrationScreenMapper.Params

interface RegistrationScreenMapper : Mapper<Params, RegistrationVM.ScreenData> {
  data class Params(
    val state: RegistrationVM.State,
    val onBackClick: () -> Unit,
    val onRegisterClick: () -> Unit,
    val onPasswordValueChanged: (String) -> Unit,
    val onPasswordFocusChanged: (Boolean) -> Unit,
    val onPasswordRepeatValueChanged: (String) -> Unit,
    val onPasswordRepeatFocusChanged: (Boolean) -> Unit,
    val onEmailValueChanged: (String) -> Unit,
    val onEmailFocusChanged: (Boolean) -> Unit,
    val onNameValueChanged: (String) -> Unit,
    val onNameFocusChanged: (Boolean) -> Unit,
  )
}

class RegistrationScreenMapperImpl : RegistrationScreenMapper {
  override fun invoke(params: Params): RegistrationVM.ScreenData =
    when (params.state) {
      is RegistrationVM.State -> RegistrationVM.ScreenData(
        tabData = TopAppBarData.Back(
          onNavigationIconClick = params.onBackClick,
        ),
        screenDescription = "Uzupełnij poniższe pola, aby się zarejestrować i móc korzystać z aplikacji!",
        registerButtonData = LargeButtonData.Primary(
          text = "Zarejestruj się",
          onClick = params.onRegisterClick,
        ),
        nameInput = TextFieldData(
          label = "Wpisz swoje imię",
          onValueChanged = params.onNameValueChanged,
          validationState = params.state.typedNameState,
          onFocusChanged = params.onNameFocusChanged,
        ),
        emailInput = TextFieldData(
          label = "Wpisz email",
          onValueChanged = params.onEmailValueChanged,
          validationState = params.state.emailState,
          onFocusChanged = params.onEmailFocusChanged,
        ),
        passwordInput = TextFieldData(
          label = "Wpisz hasło",
          onValueChanged = params.onPasswordValueChanged,
          validationState = params.state.passwordState,
          textFieldType = TextFieldType.Password,
          onFocusChanged = params.onPasswordFocusChanged,
        ),
        passwordRepeatInput = TextFieldData(
          label = "Powtórz hasło",
          onValueChanged = params.onPasswordRepeatValueChanged,
          validationState = params.state.passwordRepeatState,
          textFieldType = TextFieldType.Password,
          onFocusChanged = params.onPasswordRepeatFocusChanged,
        ),
        onBackClick = params.onBackClick,
      )
    }

}