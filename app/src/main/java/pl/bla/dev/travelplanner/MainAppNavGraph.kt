package pl.bla.dev.travelplanner

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import pl.bla.dev.common.core.navigation.AppNavController
import pl.bla.dev.feature.dashboard.presentation.DashboardDestinations
import pl.bla.dev.feature.dashboard.presentation.DashboardResults
import pl.bla.dev.feature.dashboard.presentation.dashboardNavGraph
import pl.bla.dev.feature.login.presentation.AuthDestinations
import pl.bla.dev.feature.login.presentation.AuthResults
import pl.bla.dev.feature.login.presentation.authNavGraph
import pl.bla.dev.feature.travel.presentation.TravelDestinations
import pl.bla.dev.feature.travel.presentation.TravelResults
import pl.bla.dev.feature.travel.presentation.screen.details.TravelDetailsVM
import pl.bla.dev.feature.travel.presentation.travelNavGraph

@Composable
fun MainAppNavGraph(
  onAppExit: () -> Unit,
) {
  val appNavController = AppNavController(
    navController = rememberNavController(),
  )
  val appContractVM = hiltViewModel<AppContractVM>()

  NavHost(
    navController = appNavController.navController,
    startDestination = AuthDestinations.AuthGraph.route,
  ) {
    authNavGraph(
      appContractVM = appContractVM,
      navController = appNavController,
      onResult = { result ->
        when (result) {
          AuthResults.LoginSuccess -> appNavController.navigate(
            destination = DashboardDestinations.DashboardGraph,
          )
          AuthResults.RegistrationSuccess -> appNavController.navigate(
            destination = DashboardDestinations.DashboardGraph,
          )
          AuthResults.ExitApp -> onAppExit()
        }
      },
    )

    dashboardNavGraph(
      appContractVM = appContractVM,
      navController = appNavController,
      onResult = { result ->
        when (result) {
          DashboardResults.Logout -> appNavController.navigate(
            destination = AuthDestinations.AuthGraph,
          )
          is DashboardResults.ToTravelDetails -> {
            appContractVM.setContractData(
              destination = TravelDestinations.TravelDetails,
              data = TravelDetailsVM.TravelDetailsSetupData(travelId = result.travelId),
            )
            appNavController.navigate(destination = TravelDestinations.TravelDetails)
          }
          DashboardResults.ToNewTravel ->
            appNavController.navigate(destination = TravelDestinations.NewTravelVehicle)

        }
      },
    )

    travelNavGraph(
      appContractVM = appContractVM,
      navController = appNavController,
      onResult = { result ->
        when (result) {
          TravelResults.Close -> appNavController.navigate(
            destination = DashboardDestinations.DashboardGraph,
          )
        }
      },
    )
  }
}