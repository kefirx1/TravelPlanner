package pl.bla.dev.feature.login.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import pl.bla.dev.common.core.navigation.AppNavController
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
  navigation(
    route = AuthDestinations.AuthGraph.route,
    startDestination = AuthDestinations.Login.route,
  ) {
    createDestination<Nothing, AuthContractVM, LoginVMImpl, LoginVM.Action.Navigation>(
      destination = AuthDestinations.Login,
      navController = navController,
      content = { viewModel ->
        LoginScreen(viewModel = viewModel)
      },
      navActionHandler = { action, contractViewModel ->
        when (action) {
          is LoginVM.Action.Navigation.ToDashboard -> onResult(AuthResults.LoginSuccess)
          is LoginVM.Action.Navigation.ToOnboarding -> navController.navigate(
            destination = AuthDestinations.Onboarding,
          )
          is LoginVM.Action.Navigation.Back -> onResult(AuthResults.ExitApp)
        }
      },
    )

    createDestination<RegistrationVM.RegistrationSetupData, AuthContractVM, RegistrationVMImpl, RegistrationVM.Action.Navigation>(
      destination = AuthDestinations.Registration,
      navController = navController,
      content = { viewModel ->
        RegistrationScreen(viewModel = viewModel)
      },
      navActionHandler = { action, sharedViewModel ->
        when (action) {
          is RegistrationVM.Action.Navigation.RegistrationCompleted -> onResult(AuthResults.RegistrationSuccess)
          is RegistrationVM.Action.Navigation.Back -> navController.navigate(AuthDestinations.Onboarding)
        }
      }
    )

    createDestination<Nothing, AuthContractVM, OnboardingVMImpl, OnboardingVM.Action.Navigation>(
      destination = AuthDestinations.Onboarding,
      navController = navController,
      content = { viewModel ->
        OnboardingScreen(viewModel = viewModel)
      },
      navActionHandler = { action, sharedViewModel ->
        when (action) {
          is OnboardingVM.Action.Navigation.ToRegistration -> {
            sharedViewModel.setContractData(
              destination = AuthDestinations.Registration,
              data = RegistrationVM.RegistrationSetupData(
                action.selectedChips,
              ),
            )
            navController.navigate(
              destination = AuthDestinations.Registration,
            )
          }
          is OnboardingVM.Action.Navigation.Back -> navController.popBackStack()
        }
      }
    )
  }
}
