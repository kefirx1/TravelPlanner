package pl.bla.dev.common.core.navigation

import androidx.navigation.NavHostController

data class AppNavController(
  val navController: NavHostController,
) {
  fun <T : Any> navigate(destination: T) {
    navController.navigate(destination)
  }

  fun popBackStack() {
    navController.popBackStack()
  }
}