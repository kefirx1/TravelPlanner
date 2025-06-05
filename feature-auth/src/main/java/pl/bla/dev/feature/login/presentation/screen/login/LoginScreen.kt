package pl.bla.dev.feature.login.presentation.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.common.ui.componenst.button.LargeButton
import pl.bla.dev.common.ui.componenst.emptyscreen.EmptyScreen

@Composable
fun LoginScreen(viewModel: LoginVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()

  when (val screenData = state) {
    LoginVM.ScreenData.Initial -> EmptyScreen()
    is LoginVM.ScreenData.MainScreen -> LoginScreenContent(
      data = screenData,
    )
  }


}

@Composable
fun LoginScreenContent(
  data: LoginVM.ScreenData.MainScreen,
) {
  LargeButton(buttonData = data.buttonData)

}
