package pl.bla.dev.feature.dashboard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.dashboardNavGraph(
  navController: NavController,
  onResult: () -> Unit,
  onBack: () -> Unit,
) {
  navigation<DashboardDestinations.DashboardGraph>(
    startDestination = DashboardDestinations.MainDashboard,
  ) {
    composable<DashboardDestinations.MainDashboard> {
      Text(
        "Dashbaord"
      )
    }
  }
}