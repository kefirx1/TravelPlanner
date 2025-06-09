package pl.bla.dev.feature.login.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import pl.bla.dev.common.core.navigation.AppNavController
import pl.bla.dev.common.core.navigation.DestinationType
import pl.bla.dev.common.core.navigation.createDestination
import pl.bla.dev.feature.login.presentation.screen.login.LoginScreen
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM
import pl.bla.dev.feature.login.presentation.screen.login.LoginVMImpl
import pl.bla.dev.feature.login.presentation.screen.onboarding.OnboardingScreen
import pl.bla.dev.feature.login.presentation.screen.onboarding.OnboardingVM
import pl.bla.dev.feature.login.presentation.screen.onboarding.OnboardingVMImpl
import pl.bla.dev.feature.login.presentation.screen.registration.RegistrationScreen
import pl.bla.dev.feature.login.presentation.screen.registration.RegistrationVM
import pl.bla.dev.feature.login.presentation.screen.registration.RegistrationVMImpl

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
          is LoginVM.Action.Navigation.ToOnboarding -> navController.navigate(
            destination = AuthDestinations.Onboarding,
          )
          is LoginVM.Action.Navigation.Back -> onResult(AuthResults.ExitApp)
        }
      },
    )

    createDestination<AuthDestinations.Registration, RegistrationVMImpl, RegistrationVM.Action.Navigation>(
      destinationType = DestinationType.Screen,
      setupFactoryData = true,
      content = { viewModel ->
        RegistrationScreen(viewModel = viewModel)
      },
      navActionHandler = { action ->
        when (action) {
          is RegistrationVM.Action.Navigation.Back -> navController.navigate(AuthDestinations.Onboarding)
        }
      }
    )

    createDestination<AuthDestinations.Onboarding, OnboardingVMImpl, OnboardingVM.Action.Navigation>(
      destinationType = DestinationType.Screen,
      setupFactoryData = false,
      content = { viewModel ->
        OnboardingScreen(viewModel = viewModel)
      },
      navActionHandler = { action ->
        when (action) {
          is OnboardingVM.Action.Navigation.ToRegistration -> navController.navigate(
            destination = AuthDestinations.Registration,
          )
          is OnboardingVM.Action.Navigation.Back -> navController.popBackStack()
        }
      }
    )

  }
}
