package pl.bla.dev.feature.login.presentation.screen.login.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.button.SmallButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapper.Params

interface LoginScreenMapper : Mapper<Params, LoginVM.ScreenData> {
  data class Params(
    val state: LoginVM.State,
    val onShowDialog: (DialogData) -> Unit,
  )
}

class LoginScreenMapperImpl : LoginScreenMapper {
  override fun invoke(params: Params): LoginVM.ScreenData =
    when (params.state) {
      is LoginVM.State.Initial -> LoginVM.ScreenData.MainScreen(
        yes = "",
        buttonData = LargeButtonData.Primary(
          "kliknij",
          onClick = {
            params.onShowDialog(
              DialogData(
                "Dialog",
                content = "content",
                {},
                onPrimaryButtonData = SmallButtonData.Tertiary(
                  "text",
                  onClick = {},
                )
              )
            )
          }
        )
      )
    }
}