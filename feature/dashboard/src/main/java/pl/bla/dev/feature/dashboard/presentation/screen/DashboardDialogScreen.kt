package pl.bla.dev.feature.dashboard.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.common.ui.componenst.dialog.DialogScreen

@Composable
fun DashboardDialogScreen(viewModel: DashboardDialogVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()

  DialogScreen(dialogData = state.dialogData)
}