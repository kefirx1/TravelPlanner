package pl.bla.dev.common.ui.componenst.picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import pl.bla.dev.common.ui.theming.AppColors

data class DatePickerInputData(
  val pickedDate: String,
  val onClick: () -> Unit,
)

private const val ACTION_DEBOUNCE = 100L

@OptIn(FlowPreview::class)
@Composable
fun DatePickerInput(data: DatePickerInputData) {
  val interactionSource = remember { MutableInteractionSource() }

  LaunchedEffect(interactionSource) {
    interactionSource.interactions.debounce(ACTION_DEBOUNCE).collect { interaction ->
      when (interaction) {
        is PressInteraction -> data.onClick()
      }
    }
  }

  OutlinedTextField(
    modifier = Modifier
      .fillMaxWidth(),
    readOnly = true,
    value = data.pickedDate,
    onValueChange = {},
    maxLines = 1,
    trailingIcon = {
      Icon(
        imageVector = Icons.Default.DateRange,
        contentDescription = "Date picker icon",
        tint = AppColors.blue2,
      )
    },
    colors = OutlinedTextFieldDefaults.colors().copy(
      focusedTextColor = AppColors.white,
      unfocusedTextColor = AppColors.white,
      focusedContainerColor = AppColors.grey,
      unfocusedContainerColor = AppColors.grey,
      focusedIndicatorColor = AppColors.blue2,
      unfocusedIndicatorColor = AppColors.blue2,
      focusedTrailingIconColor = AppColors.blue2,
      unfocusedTrailingIconColor = AppColors.blue2,
    ),
    interactionSource = interactionSource,
  )
}

private class DatePickerInputProvider : PreviewParameterProvider<DatePickerInputData> {
  override val values: Sequence<DatePickerInputData> = sequenceOf(
    DatePickerInputData(
      pickedDate = "01-01-2025",
      onClick = {},
    )
  )
}

@Preview(name = "DatePickerInput preview")
@Composable
private fun DatePickerInputPreview(
  @PreviewParameter(DatePickerInputProvider::class) data: DatePickerInputData,
) {
  DatePickerInput(data)
}