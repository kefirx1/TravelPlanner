package pl.bla.dev.common.ui.componenst.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import pl.bla.dev.common.ui.componenst.text.CustomText
import pl.bla.dev.common.ui.theming.AppColors

sealed class LargeButtonData(
  open val text: String,
  open val onClick: () -> Unit,
) {
  data class Primary(
    override val text: String,
    override val onClick: () -> Unit,
  ) : LargeButtonData(
    text = text,
    onClick = onClick,
  )

  data class Secondary(
    override val text: String,
    override val onClick: () -> Unit,
  ) : LargeButtonData(
    text = text,
    onClick = onClick,
  )

  data class Tertiary(
    override val text: String,
    override val onClick: () -> Unit,
  ) : LargeButtonData(
    text = text,
    onClick = onClick,
  )
}

@Composable
fun LargeButton(
  modifier: Modifier = Modifier,
  buttonData: LargeButtonData,
) {
  Button(
    modifier = modifier,
    onClick = buttonData.onClick,
    shape = when (buttonData) {
      is LargeButtonData.Primary -> ButtonDefaults.shape
      is LargeButtonData.Secondary -> ButtonDefaults.shape
      is LargeButtonData.Tertiary -> ButtonDefaults.outlinedShape
    },
    border = when (buttonData) {
      is LargeButtonData.Primary,
      is LargeButtonData.Secondary -> null
      is LargeButtonData.Tertiary -> BorderStroke(
        width = 1.dp,
        color = AppColors.blue2,
      )
    },
    colors = ButtonDefaults.buttonColors().copy(
      containerColor = when (buttonData) {
        is LargeButtonData.Primary -> AppColors.blue2
        is LargeButtonData.Secondary -> AppColors.blue
        is LargeButtonData.Tertiary -> AppColors.transparent
      },
    ),
    content = {
      CustomText(
        text = buttonData.text,
        style = MaterialTheme.typography.labelLarge,
        color = when (buttonData) {
          is LargeButtonData.Primary,
          is LargeButtonData.Secondary -> AppColors.black
          is LargeButtonData.Tertiary -> AppColors.white
        }
      )
    }
  )
}

private class LargeButtonPreviewProvider : PreviewParameterProvider<LargeButtonData> {
  override val values: Sequence<LargeButtonData> = sequenceOf(
    LargeButtonData.Primary(
      text = "button primary",
      onClick = {},
    ),
    LargeButtonData.Secondary(
      text = "button secondary",
      onClick = {},
    ),
    LargeButtonData.Tertiary(
      text = "button tertiary",
      onClick = {},
    ),
  )
}

@Preview(name = "LargeButton preview")
@Composable
private fun LargeButtonPreview(
  @PreviewParameter(LargeButtonPreviewProvider::class) buttonData: LargeButtonData,
) {
  LargeButton(buttonData = buttonData)
}