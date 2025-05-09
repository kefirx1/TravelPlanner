package pl.bla.dev.common.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.consumeEach

@Composable
fun <NAV> NavActionHandler(viewModel: CustomViewModel<*, *, NAV>, handler: (NAV) -> Unit) {
  LaunchedEffect(viewModel.navAction) {
    viewModel.navAction.consumeEach {  action ->
      handler(action)
      viewModel.viewModelScope.cancel()
    }
  }
}