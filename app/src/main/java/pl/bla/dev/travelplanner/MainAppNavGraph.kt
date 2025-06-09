package pl.bla.dev.travelplanner

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import pl.bla.dev.common.core.navigation.AppNavController
import pl.bla.dev.feature.dashboard.presentation.DashboardDestinations
import pl.bla.dev.feature.dashboard.presentation.DashboardResults
import pl.bla.dev.feature.dashboard.presentation.dashboardNavGraph
import pl.bla.dev.feature.login.presentation.AuthDestinations
import pl.bla.dev.feature.login.presentation.AuthResults
import pl.bla.dev.feature.login.presentation.authNavGraph

@Composable
fun MainAppNavGraph(
  onAppExit: () -> Unit,
) {
  val appNavController = AppNavController(
    navController = rememberNavController(),
  )

  NavHost(
    navController = appNavController.navController,
    startDestination = AuthDestinations.AuthGraph.route,
  ) {
    authNavGraph(
      navController = appNavController,
      onResult = { result ->
        when (result) {
          AuthResults.LoginSuccess -> appNavController.navigate(DashboardDestinations.DashboardGraph)
          AuthResults.ExitApp -> onAppExit()
        }
      },
    )

    dashboardNavGraph(
      navController = appNavController,
      onResult = { result ->
        when (result) {
          DashboardResults.Logout -> appNavController.popBackStack()
        }
      },
    )
  }
}