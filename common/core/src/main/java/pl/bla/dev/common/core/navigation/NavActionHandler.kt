package pl.bla.dev.common.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.consumeEach
import pl.bla.dev.common.core.viewmodel.ContractViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModel

@Composable
fun <NAV> NavActionHandler(viewModel: CustomViewModel<*, *, NAV>, handler: (NAV, ContractViewModel) -> Unit, sharedViewModel: ContractViewModel) {
  LaunchedEffect(viewModel.navAction) {
    viewModel.navAction.collect {  action ->
      handler(action, sharedViewModel)
    }
  }
}