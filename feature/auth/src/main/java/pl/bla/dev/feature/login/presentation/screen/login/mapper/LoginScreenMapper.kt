package pl.bla.dev.feature.login.presentation.screen.login.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.button.SmallButtonData
import pl.bla.dev.common.ui.componenst.input.TextFieldData
import pl.bla.dev.common.ui.componenst.input.TextFieldType
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapper.Params

interface LoginScreenMapper : Mapper<Params, LoginVM.ScreenData> {
  data class Params(
    val state: LoginVM.State,
    val onLoginClick: () -> Unit,
    val onStartClick: () -> Unit,
    val onPasswordValueChanged: (String) -> Unit,
    val onForgotPasswordClick: () -> Unit,
    val onBackClick: () -> Unit,
  )
}

class LoginScreenMapperImpl : LoginScreenMapper {
  override fun invoke(params: Params): LoginVM.ScreenData =
    when (params.state) {
      LoginVM.State.Initial -> LoginVM.ScreenData.Initial(
        onBackClick = params.onBackClick,
      )
      is LoginVM.State.Login -> LoginVM.ScreenData.LoginScreen(
        appName = "TravelPlanner",
        welcomeLabel = "Witaj ${params.state.userName}!",
        textFieldData = TextFieldData(
          hint = "Wpisz hasło",
          validationState = params.state.passwordState,
          onValueChanged = params.onPasswordValueChanged,
          textFieldType = TextFieldType.Password,
          linkTextButton = SmallButtonData.Tertiary(
            text = "Nie pamiętasz hasła?",
            onClick = {
              params.onForgotPasswordClick()
            },
          )
        ),
        buttonData = LargeButtonData.Primary(
          text = "Zaloguj",
          onClick = {
            params.onLoginClick()
          },
        ),
        onBackClick = params.onBackClick,
      )
      is LoginVM.State.Registration -> LoginVM.ScreenData.RegistrationScreen(
        appName = "TravelPlanner",
        buttonData = LargeButtonData.Primary(
          text = "Rozpocznij",
          onClick = {
            params.onStartClick()
          },
        ),
        onBackClick = params.onBackClick,
      )
    }
}