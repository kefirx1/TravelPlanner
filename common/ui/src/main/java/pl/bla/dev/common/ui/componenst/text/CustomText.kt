package pl.bla.dev.common.ui.componenst.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import pl.bla.dev.common.ui.theming.AppColors

@Composable
fun CustomText(
  modifier: Modifier = Modifier,
  text: String,
  style: TextStyle = MaterialTheme.typography.bodyMedium,
  color: Color = AppColors.white,
) {
  Text(
    modifier = modifier,
    text = text,
    color = color,
    style = style,
  )
}