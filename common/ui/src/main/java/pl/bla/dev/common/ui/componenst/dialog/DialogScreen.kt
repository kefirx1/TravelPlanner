package pl.bla.dev.common.ui.componenst.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun DialogScreen(dialogData: DialogData) {
  var showDialog by remember { mutableStateOf(true) }

  if (showDialog) {
    Dialog(dialogData = dialogData)
  }
}