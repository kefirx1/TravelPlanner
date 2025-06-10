package pl.bla.dev.feature.dashboard.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import pl.bla.dev.common.core.navigation.AppNavController
import pl.bla.dev.common.core.navigation.DestinationType
import pl.bla.dev.common.core.navigation.createDestination
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.feature.dashboard.presentation.screen.dialog.DashboardDialogScreen
import pl.bla.dev.feature.dashboard.presentation.screen.dialog.DashboardDialogVM
import pl.bla.dev.feature.dashboard.presentation.screen.dialog.DashboardDialogVMImpl
import pl.bla.dev.feature.dashboard.presentation.screen.main.MainDashboardScreen
import pl.bla.dev.feature.dashboard.presentation.screen.main.MainDashboardVM
import pl.bla.dev.feature.dashboard.presentation.screen.main.MainDashboardVMImpl

fun NavGraphBuilder.dashboardNavGraph(
  navController: AppNavController,
  onResult: (DashboardResults) -> Unit,
) {
  navigation(
    route = DashboardDestinations.DashboardGraph.route,
    startDestination = DashboardDestinations.MainDashboard.route,
  ) {
    createDestination<Nothing, DashboardContractVM, MainDashboardVMImpl, MainDashboardVM.Action.Navigation>(
      destination = DashboardDestinations.MainDashboard,
      navController = navController,
      content = { viewModel ->
        MainDashboardScreen(viewModel = viewModel)
      },
      navActionHandler = { action, contractViewModel ->
        when (action) {
          is MainDashboardVM.Action.Navigation.Logout -> onResult(DashboardResults.Logout)
          is MainDashboardVM.Action.Navigation.ShowDialog -> {
            contractViewModel.setContractData(
              destination = DashboardDestinations.DashboardDialog,
              data = action.dialogData,
            )
            navController.navigate(DashboardDestinations.DashboardDialog)
          }
        }
      }
    )

    createDestination<DialogData, DashboardContractVM, DashboardDialogVMImpl, DashboardDialogVM.Action.Navigation>(
      destination = DashboardDestinations.DashboardDialog,
      destinationType = DestinationType.Dialog,
      navController = navController,
      content = { viewModel ->
        DashboardDialogScreen(viewModel = viewModel)
      },
      navActionHandler = { action, _ ->
        when (action) {
          is DashboardDialogVM.Action.Navigation.OnDialogAction -> {
            navController.popBackStack()
            action.dailogAction()
          }
        }
      }
    )

  }
}