package pl.bla.dev.common.ui.componenst.divider

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pl.bla.dev.common.ui.theming.AppColors

@Composable
fun Divider(
  spacer: Dp = 0.dp,
  color: Color = AppColors.grey2
) {
  Spacer(modifier = Modifier.height(spacer))
  HorizontalDivider(
    thickness = 2.dp,
    color = color,
  )
  Spacer(modifier = Modifier.height(spacer))
}