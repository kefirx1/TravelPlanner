package pl.bla.dev.common.ui.componenst.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import pl.bla.dev.common.ui.componenst.text.CustomText
import pl.bla.dev.common.ui.theming.AppColors

sealed class SmallButtonData(
  open val text: String,
  open val onClick: () -> Unit,
) {
  data class Primary(
    override val text: String,
    override val onClick: () -> Unit,
  ) : SmallButtonData(
    text = text,
    onClick = onClick,
  )

  data class Secondary(
    override val text: String,
    override val onClick: () -> Unit,
  ) : SmallButtonData(
    text = text,
    onClick = onClick,
  )


  data class Tertiary(
    override val text: String,
    override val onClick: () -> Unit,
  ) : SmallButtonData(
    text = text,
    onClick = onClick,
  )
}

@Composable
fun SmallButton(
  modifier: Modifier = Modifier,
  buttonData: SmallButtonData,
) {
  Button(
    modifier = modifier,
    onClick = buttonData.onClick,
    shape = when (buttonData) {
      is SmallButtonData.Primary -> ButtonDefaults.shape
      is SmallButtonData.Secondary -> ButtonDefaults.outlinedShape
      is SmallButtonData.Tertiary -> ButtonDefaults.shape
    },
    border = when (buttonData) {
      is SmallButtonData.Primary -> null
      is SmallButtonData.Secondary -> BorderStroke(
        width = (0.5).dp,
        color = AppColors.blue2,
      )
      is SmallButtonData.Tertiary -> null
    },
    colors = ButtonDefaults.buttonColors().copy(
      containerColor = when (buttonData) {
        is SmallButtonData.Primary -> AppColors.blue2
        is SmallButtonData.Secondary -> AppColors.transparent
        is SmallButtonData.Tertiary -> AppColors.transparent
      },
    ),
    contentPadding = ButtonDefaults.TextButtonContentPadding,
    content = {
      CustomText(
        text = buttonData.text,
        style = MaterialTheme.typography.bodyMedium,
        color = when (buttonData) {
          is SmallButtonData.Primary -> AppColors.black
          is SmallButtonData.Secondary -> AppColors.black
          is SmallButtonData.Tertiary -> AppColors.blue2
        },
      )
    }
  )
}

private class SmallButtonPreviewProvider : PreviewParameterProvider<SmallButtonData> {
  override val values: Sequence<SmallButtonData> = sequenceOf(
    SmallButtonData.Primary(
      text = "button primary",
      onClick = {},
    ),
    SmallButtonData.Secondary(
      text = "button secondary",
      onClick = {},
    ),
    SmallButtonData.Tertiary(
      text = "button tertiary",
      onClick = {},
    ),
  )
}

@Preview(name = "SmallButton preview", showSystemUi = true)
@Composable
private fun SmallButtonPreview(
  @PreviewParameter(SmallButtonPreviewProvider::class) buttonData: SmallButtonData,
) {
  SmallButton(buttonData = buttonData)
}