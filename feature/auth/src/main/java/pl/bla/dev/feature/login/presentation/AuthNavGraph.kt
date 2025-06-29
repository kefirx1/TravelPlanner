package pl.bla.dev.feature.login.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import pl.bla.dev.common.core.navigation.AppNavController
import pl.bla.dev.common.core.navigation.DestinationType
import pl.bla.dev.common.core.navigation.createDestination
import pl.bla.dev.common.core.viewmodel.ContractViewModel
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.feature.login.presentation.screen.dialog.AuthDialogScreen
import pl.bla.dev.feature.login.presentation.screen.dialog.AuthDialogVM
import pl.bla.dev.feature.login.presentation.screen.dialog.AuthDialogVMImpl
import pl.bla.dev.feature.login.presentation.screen.login.LoginScreen
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM
import pl.bla.dev.feature.login.presentation.screen.login.LoginVMImpl
import pl.bla.dev.feature.login.presentation.screen.onboarding.OnboardingScreen
import pl.bla.dev.feature.login.presentation.screen.onboarding.OnboardingVM
import pl.bla.dev.feature.login.presentation.screen.onboarding.OnboardingVMImpl
import pl.bla.dev.feature.login.presentation.screen.personalinfo.PersonalInfoScreen
import pl.bla.dev.feature.login.presentation.screen.personalinfo.PersonalInfoVM
import pl.bla.dev.feature.login.presentation.screen.personalinfo.PersonalInfoVMImpl
import pl.bla.dev.feature.login.presentation.screen.registration.RegistrationScreen
import pl.bla.dev.feature.login.presentation.screen.registration.RegistrationVM
import pl.bla.dev.feature.login.presentation.screen.registration.RegistrationVMImpl

fun NavGraphBuilder.authNavGraph(
  appContractVM: ContractViewModel,
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
          is LoginVM.Action.Navigation.ShowDialog -> {
            contractViewModel.setContractData(
              destination = AuthDestinations.AuthDialog,
              data = action.dialogData,
            )

            navController.navigate(AuthDestinations.AuthDialog)
          }
          is LoginVM.Action.Navigation.Back -> onResult(AuthResults.ExitApp)
        }
      },
    )

    createDestination<PersonalInfoVM.PersonalInfoSetupData, AuthContractVM, PersonalInfoVMImpl, PersonalInfoVM.Action.Navigation>(
      destination = AuthDestinations.PersonalInfo,
      navController = navController,
      content = { viewModel ->
        PersonalInfoScreen(viewModel = viewModel)
      },
      navActionHandler = { action, sharedViewModel ->
        when (action) {
          is PersonalInfoVM.Action.Navigation.ToRegistration -> {
            sharedViewModel.setContractData(
              destination = AuthDestinations.Registration,
              data = RegistrationVM.RegistrationSetupData(
                selectedChips = action.selectedChips,
                email = action.email,
                userName = action.userName,
              ),
            )
            navController.navigate(
              destination = AuthDestinations.Registration,
            )
          }
          is PersonalInfoVM.Action.Navigation.Back -> navController.navigate(AuthDestinations.Onboarding)
        }
      }
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
          is RegistrationVM.Action.Navigation.Back -> navController.navigate(AuthDestinations.PersonalInfo)
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
          is OnboardingVM.Action.Navigation.ToPersonalInfo -> {
            sharedViewModel.setContractData(
              destination = AuthDestinations.PersonalInfo,
              data = PersonalInfoVM.PersonalInfoSetupData(
                action.selectedChips,
              ),
            )
            navController.navigate(
              destination = AuthDestinations.PersonalInfo,
            )
          }
          is OnboardingVM.Action.Navigation.Back -> navController.popBackStack()
        }
      }
    )

    createDestination<DialogData, AuthContractVM, AuthDialogVMImpl, AuthDialogVM.Action.Navigation>(
      destination = AuthDestinations.AuthDialog,
      destinationType = DestinationType.Dialog,
      navController = navController,
      content = { viewModel ->
        AuthDialogScreen(viewModel = viewModel)
      },
      navActionHandler = { action, _ ->
        when (action) {
          is AuthDialogVM.Action.Navigation.OnDialogAction -> {
            navController.popBackStack()
            action.dialogAction()
          }
        }
      }
    )
  }
}
