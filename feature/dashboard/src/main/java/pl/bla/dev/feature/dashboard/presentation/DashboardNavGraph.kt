package pl.bla.dev.feature.dashboard.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
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
        navController.navigate(DashboardDestinations.DashboardDialog(
          DashboardDestinations.DashboardDialog.Types.LOGOUT_DIALOG)
        )
      }
    }

    createDestination<DashboardDestinations.DashboardDialog, DashboardDialogVMImpl, DashboardDialogVM.Action.Navigation>(
      destinationType = DestinationType.Dialog,
      setupFactoryData = true,
      content = { viewModel ->
        DashboardDialogScreen(viewModel = viewModel)
      },
      navActionHandler = { action ->
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