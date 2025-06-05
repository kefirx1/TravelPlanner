package pl.bla.dev.common.ui.componenst.emptyscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.bla.dev.common.ui.theming.AppColors

@Composable
fun EmptyScreen() {
  Column(
    modifier = Modifier.fillMaxSize()
      .background(color = AppColors.grey),
  ){}
}