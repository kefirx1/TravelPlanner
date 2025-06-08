package pl.bla.dev.feature.login.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import pl.bla.dev.common.core.navigation.AppNavController
import pl.bla.dev.common.core.navigation.DestinationType
import pl.bla.dev.common.core.navigation.createDestination
import pl.bla.dev.feature.login.presentation.screen.login.LoginScreen
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM
import pl.bla.dev.feature.login.presentation.screen.login.LoginVMImpl

fun NavGraphBuilder.authNavGraph(
  navController: AppNavController,
  onResult: (AuthResults) -> Unit,
) {
  navigation<AuthDestinations.AuthGraph>(
    startDestination = AuthDestinations.Login,
  ) {
    createDestination<AuthDestinations.Login, LoginVMImpl, LoginVM.Action.Navigation>(
      destinationType = DestinationType.Screen,
      setupFactoryData = false,
      content = { viewModel ->
        LoginScreen(viewModel = viewModel)
      },
      navActionHandler = { action ->
        when (action) {
          is LoginVM.Action.Navigation.ToDashboard -> onResult(AuthResults.LoginSuccess)
          is LoginVM.Action.Navigation.ToRegistration -> {}
          is LoginVM.Action.Navigation.Back -> onResult(AuthResults.ExitApp)
        }
      },
    )
  }
}
