package pl.bla.dev.feature.dashboard.presentation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.bla.dev.common.core.navigation.AppNavController
import pl.bla.dev.common.core.navigation.DestinationType
import pl.bla.dev.common.core.navigation.createDestination
import pl.bla.dev.common.core.navigation.rememberContractViewModel
import pl.bla.dev.common.ui.componenst.button.SmallButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.feature.dashboard.presentation.screen.DashboardDialogScreen
import pl.bla.dev.feature.dashboard.presentation.screen.DashboardDialogVM
import pl.bla.dev.feature.dashboard.presentation.screen.DashboardDialogVMImpl

fun NavGraphBuilder.dashboardNavGraph(
  navController: AppNavController,
  onResult: (DashboardResults) -> Unit,
) {
  navigation(
    route = DashboardDestinations.DashboardGraph.route,
    startDestination = DashboardDestinations.MainDashboard.route,
  ) {
    composable(
      route = DashboardDestinations.MainDashboard.route,
    ) {
      val sharedViewmodel = rememberContractViewModel<DashboardContractVM>(
        navController = navController,
      )

      BackHandler {
        sharedViewmodel.setContractData(
          destination = DashboardDestinations.DashboardDialog,
          data = DialogData(
            title = "Chcesz się wylogować?",
            content = "Kliknij wyloguj, aby wyjść z aplikacji",
            onDismiss = {
              navController.popBackStack()
            },
            onPrimaryButtonData = SmallButtonData.Tertiary(
              text = "Wyloguj",
              onClick = {
                navController.popBackStack()
                onResult(DashboardResults.Logout)
              },
            ),
            onSecondaryButtonData = SmallButtonData.Tertiary(
              text = "Nie",
              onClick = {
                navController.popBackStack()
              },
            ),
          )
        )

        navController.navigate(DashboardDestinations.DashboardDialog)
      }
    }

    createDestination<DialogData, DashboardContractVM,  DashboardDialogVMImpl, DashboardDialogVM.Action.Navigation>(
      destination = DashboardDestinations.DashboardDialog,
      destinationType = DestinationType.Dialog,
      navController = navController,
      content = { viewModel ->
        DashboardDialogScreen(viewModel = viewModel)
      },
    )

  }
}