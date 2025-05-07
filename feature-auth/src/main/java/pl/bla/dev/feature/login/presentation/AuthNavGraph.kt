package pl.bla.dev.feature.login.presentation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.authNavGraph(
  navController: NavController,
  onResult: () -> Unit,
  onBack: () -> Unit,
) {
  navigation<AuthDestinations.AuthGraph>(
    startDestination = AuthDestinations.Login,
  ) {
    composable<AuthDestinations.Login> {
      Text(text = "Login")
    }
  }
}