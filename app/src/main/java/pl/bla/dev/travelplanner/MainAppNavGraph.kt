package pl.bla.dev.travelplanner

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import pl.bla.dev.feature.login.presentation.AuthDestinations
import pl.bla.dev.feature.login.presentation.authNavGraph

@Composable
fun MainAppNavGraph() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = AuthDestinations.AuthGraph,
  ) {
    authNavGraph(
      navController = navController,
      onResult = { navController.navigate("") },
      onBack = { navController.popBackStack() },
    )

  }
}