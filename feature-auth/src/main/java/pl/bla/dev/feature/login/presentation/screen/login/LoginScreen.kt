package pl.bla.dev.feature.login.presentation.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(viewModel: LoginVM) {
  val state by viewModel.screenData.collectAsState()

  when (val screenData = state) {
    is LoginVM.ScreenData.MainScreen -> LoginScreenContent(
      data = screenData,
    )
  }
}

@Composable
fun LoginScreenContent(
  data: LoginVM.ScreenData.MainScreen,
) {

}
