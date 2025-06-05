package pl.bla.dev.common.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory

sealed interface DestinationType {
  data object Screen : DestinationType
  data object Dialog : DestinationType
}

inline fun <reified DEST: Destination, reified VM: CustomViewModel<*, *, NAV>, NAV> NavGraphBuilder.createDestination(
  destinationType: DestinationType,
  setupFactoryData: Boolean,
  noinline navActionHandler: (NAV) -> Unit = {},
  crossinline content: @Composable (VM) -> Unit
) {
  when (destinationType) {
    DestinationType.Screen -> composable<DEST> { navBackStack ->
      val viewModel = if (setupFactoryData) hiltViewModel(
        creationCallback = { factory: CustomViewModelFactory<DEST, VM> ->
          factory.setup(setupData = navBackStack.toRoute<DEST>())
        }
      ) else hiltViewModel<VM>()
      NavActionHandler(viewModel = viewModel, handler = navActionHandler)
      content(viewModel)
    }
    DestinationType.Dialog -> dialog<DEST> { navBackStack ->
      val viewModel = if (setupFactoryData) hiltViewModel(
        creationCallback = { factory: CustomViewModelFactory<DEST, VM> ->
          factory.setup(setupData = navBackStack.toRoute<DEST>())
        }
      ) else hiltViewModel<VM>()
      NavActionHandler(viewModel = viewModel, handler = navActionHandler)
      content(viewModel)
    }
  }

}
