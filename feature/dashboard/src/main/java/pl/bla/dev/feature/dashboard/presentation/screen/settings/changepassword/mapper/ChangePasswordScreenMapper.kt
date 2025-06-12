package pl.bla.dev.feature.dashboard.presentation.screen.settings.changepassword.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.input.TextFieldData
import pl.bla.dev.common.ui.componenst.input.TextFieldType
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.dashboard.presentation.screen.settings.changepassword.ChangePasswordVM

interface ChangePasswordScreenMapper : Mapper<ChangePasswordScreenMapper.Params, ChangePasswordVM.ScreenData> {
  data class Params(
    val state: ChangePasswordVM.State,
    val onBackClick: () -> Unit,
    val onChangePasswordClick: () -> Unit,
    val onPasswordValueChanged: (String) -> Unit,
    val onPasswordFocusChanged: (Boolean) -> Unit,
    val onPasswordRepeatValueChanged: (String) -> Unit,
    val onPasswordRepeatFocusChanged: (Boolean) -> Unit,
  )
}

internal class ChangePasswordScreenMapperImpl : ChangePasswordScreenMapper {
  override fun invoke(params: ChangePasswordScreenMapper.Params): ChangePasswordVM.ScreenData =
    when (params.state) {
      is ChangePasswordVM.State.Initialized -> ChangePasswordVM.ScreenData.Initialized(
        tabData = TopAppBarData.Back(
          onNavigationIconClick = params.onBackClick,
        ),
        screenDescription = "Ustaw nowe, silne hasło",
        screenBody = "Hasło powinno mieć minimum 6 znaków, zawierać wielkie i małe litery cyfrę oraz znak specjalny",
        changePasswordButtonData = LargeButtonData.Primary(
          text = "Zmień hasło",
          onClick = params.onChangePasswordClick,
        ),
        passwordInput = TextFieldData(
          label = "Wpisz nowe hasło",
          onValueChanged = params.onPasswordValueChanged,
          validationState = params.state.passwordState,
          textFieldType = TextFieldType.Password,
          onFocusChanged = params.onPasswordFocusChanged,
        ),
        passwordRepeatInput = TextFieldData(
          label = "Powtórz nowe hasło",
          onValueChanged = params.onPasswordRepeatValueChanged,
          validationState = params.state.passwordRepeatState,
          textFieldType = TextFieldType.Password,
          onFocusChanged = params.onPasswordRepeatFocusChanged,
        ),
        onBackClick = params.onBackClick,
      )
    }

}