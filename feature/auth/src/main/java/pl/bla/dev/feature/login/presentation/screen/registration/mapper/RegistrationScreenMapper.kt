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
  )
}

class RegistrationScreenMapperImpl : RegistrationScreenMapper {
  override fun invoke(params: Params): RegistrationVM.ScreenData =
    when (params.state) {
      is RegistrationVM.State -> RegistrationVM.ScreenData(
        tabData = TopAppBarData.Back(
          onNavigationIconClick = params.onBackClick,
        ),
        screenDescription = "Ustaw silne hasło do aplikacji, aby móc bezpiecznie z niej korzystać",
        screenBody = "Hasło powinno mieć minimum 6 znaków, zawierać wielkie i małe litery cyfrę oraz znak specjalny",
        registerButtonData = LargeButtonData.Primary(
          text = "Zarejestruj się",
          onClick = params.onRegisterClick,
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