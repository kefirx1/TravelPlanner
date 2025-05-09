package pl.bla.dev.feature.login.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import pl.bla.dev.common.ui.viewmodel.NavActionHandler
import pl.bla.dev.feature.login.presentation.screen.login.LoginScreen
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM
import pl.bla.dev.feature.login.presentation.screen.login.LoginVMImpl

fun NavGraphBuilder.authNavGraph(
  navController: NavController,
  onResult: (AuthResults) -> Unit,
  onBack: () -> Unit,
) {
  navigation<AuthDestinations.AuthGraph>(
    startDestination = AuthDestinations.Login,
  ) {
    composable<AuthDestinations.Login> {
      val viewModel = hiltViewModel<LoginVMImpl>()

      NavActionHandler(viewModel = viewModel) { action ->
        when (action) {
          is LoginVM.Action.Navigation.Back -> onBack()
        }
      }

      LoginScreen(viewModel = viewModel)
    }
  }
}