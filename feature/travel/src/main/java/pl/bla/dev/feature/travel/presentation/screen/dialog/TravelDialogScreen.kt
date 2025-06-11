package pl.bla.dev.feature.travel.presentation.screen.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.common.ui.componenst.dialog.DialogScreen

@Composable
fun TravelDialogScreen(viewModel: TravelDialogVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()

  DialogScreen(dialogData = state.dialogData)
}