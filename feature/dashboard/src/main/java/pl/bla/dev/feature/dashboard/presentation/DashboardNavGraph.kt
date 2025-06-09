package pl.bla.dev.feature.dashboard.presentation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.bla.dev.common.core.navigation.AppNavController
import pl.bla.dev.common.core.navigation.DestinationType
import pl.bla.dev.common.core.navigation.createDestination
import pl.bla.dev.feature.dashboard.presentation.screen.DashboardDialogScreen
import pl.bla.dev.feature.dashboard.presentation.screen.DashboardDialogVM
import pl.bla.dev.feature.dashboard.presentation.screen.DashboardDialogVMImpl

fun NavGraphBuilder.dashboardNavGraph(
  navController: AppNavController,
  onResult: (DashboardResults) -> Unit,
) {
  navigation<DashboardDestinations.DashboardGraph>(
    startDestination = DashboardDestinations.MainDashboard,
  ) {
    composable<DashboardDestinations.MainDashboard> {
      BackHandler {
        navController.navigate(DashboardDestinations.DashboardDialog)
      }
    }

    createDestination<DashboardDialogVM.DialogSetupData, DashboardContractVM,  DashboardDialogVMImpl, DashboardDialogVM.Action.Navigation>(
      destination = DashboardDestinations.DashboardDialog,
      destinationType = DestinationType.Dialog,
      navController = navController,
      content = { viewModel ->
        DashboardDialogScreen(viewModel = viewModel)
      },
      navActionHandler = { action, sharedViewModel ->
        when (action) {
          is DashboardDialogVM.Action.Navigation.OnDismiss -> {
            navController.popBackStack()
          }
          is DashboardDialogVM.Action.Navigation.OnLogout -> {
            navController.popBackStack()
            onResult(DashboardResults.Logout)
          }
        }
      }
    )

  }
}