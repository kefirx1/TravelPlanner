package pl.bla.dev.common.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import pl.bla.dev.common.core.viewmodel.ContractViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory

sealed interface DestinationType {
  data object Screen : DestinationType
  data object Dialog : DestinationType
}

@Composable
inline fun <reified CVM: ContractViewModel> rememberContractViewModel(navController: AppNavController): CVM {
  val currentBackStackEntry = navController.navController.currentBackStackEntry
  val parentGraphRoute = currentBackStackEntry?.destination?.parent?.route
    ?: throw IllegalStateException("Parent graph not found")

  val parentEntry = remember(currentBackStackEntry) {
    navController.navController.getBackStackEntry(parentGraphRoute)
  }

  return hiltViewModel(parentEntry)
}

inline fun <CONTRACT: Any?, reified CVM: ContractViewModel, reified VM: CustomViewModel<*, *, NAV>, NAV> NavGraphBuilder.createDestination(
  destination: Destination,
  destinationType: DestinationType = DestinationType.Screen,
  navController: AppNavController,
  graphInitContract: ContractViewModel? = null,
  noinline navActionHandler: (NAV, ContractViewModel) -> Unit = { _, _ -> },
  crossinline content: @Composable (VM) -> Unit
) {

  when (destinationType) {
    DestinationType.Screen -> composable(route = destination.route) {
      val sharedViewModel = rememberContractViewModel<CVM>(
        navController = navController,
      )
      graphInitContract?.run {
        retrieveData<CONTRACT>(destination = destination)?.let { initData ->
          sharedViewModel.setContractData(
            destination = destination,
            data = initData,
          )
        }
      }
      val setupData = sharedViewModel.retrieveData<CONTRACT>(destination = destination)

      val viewModel = if (setupData != null) {
        hiltViewModel(
          creationCallback = { factory: CustomViewModelFactory<CONTRACT, VM> ->
            factory.setup(setupData = setupData)
          }
        )
      } else { hiltViewModel<VM>() }

      NavActionHandler(viewModel = viewModel, handler = navActionHandler, sharedViewModel = sharedViewModel)
      content(viewModel)
    }
    DestinationType.Dialog -> dialog(route = destination.route) {
      val sharedViewModel = rememberContractViewModel<CVM>(
        navController = navController,
      )
      graphInitContract?.run {
        retrieveData<CONTRACT>(destination = destination)?.let { initData ->
          sharedViewModel.setContractData(
            destination = destination,
            data = initData,
          )
        }
      }
      val setupData = sharedViewModel.retrieveData<CONTRACT>(destination = destination)

      val viewModel = if (setupData != null) {
        hiltViewModel(
          creationCallback = { factory: CustomViewModelFactory<CONTRACT, VM> ->
            factory.setup(setupData = setupData)
          }
        )
      } else { hiltViewModel<VM>() }
      NavActionHandler(viewModel = viewModel, handler = navActionHandler, sharedViewModel = sharedViewModel)
      content(viewModel)
    }
  }

}
