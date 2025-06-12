package pl.bla.dev.common.ui.componenst.picker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import pl.bla.dev.common.ui.componenst.button.SmallButton
import pl.bla.dev.common.ui.componenst.button.SmallButtonData
import pl.bla.dev.common.ui.componenst.text.CustomText
import pl.bla.dev.common.ui.theming.AppColors
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class CustomDatePickerData(
  val pickerTitle: String,
  val pickedDate: LocalDateTime,
  val onNewDatePicked: (LocalDateTime) -> Unit,
  val onDismiss: () -> Unit,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(data: CustomDatePickerData) {
  val state = rememberDatePickerState(
    initialSelectedDateMillis = data.pickedDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
    initialDisplayMode = DisplayMode.Picker,
  )
  val dateFormatter = remember { DatePickerDefaults.dateFormatter() }
  val datePickerColors = DatePickerDefaults.colors(
    containerColor = AppColors.grey,
    titleContentColor = AppColors.white,
    headlineContentColor = AppColors.white,
    weekdayContentColor = AppColors.white,
    subheadContentColor = AppColors.white,
    navigationContentColor = AppColors.white,
    yearContentColor = AppColors.white,
    disabledYearContentColor = AppColors.white,
    currentYearContentColor = AppColors.white,
    selectedYearContentColor = AppColors.white,
    disabledSelectedYearContentColor = AppColors.blue2,
    dayContentColor = AppColors.white,
    disabledDayContentColor = AppColors.white,
    selectedDayContentColor = AppColors.white,
    disabledSelectedDayContentColor = AppColors.white,
    todayContentColor = AppColors.white,
    selectedYearContainerColor = AppColors.blue2,
    disabledSelectedYearContainerColor = AppColors.blue2,
    selectedDayContainerColor = AppColors.blue2,
    disabledSelectedDayContainerColor = AppColors.blue2,
    todayDateBorderColor = AppColors.blue2,
    dayInSelectionRangeContentColor = AppColors.white,
    dayInSelectionRangeContainerColor = AppColors.blue2,
    dividerColor = AppColors.blue2,
  )

  DatePickerDialog(
    onDismissRequest = data.onDismiss,
    dismissButton = {
      SmallButton(
        buttonData = SmallButtonData.Tertiary(
          text = "Anuluj",
          onClick = data.onDismiss,
        )
      )
    },
    confirmButton = {
      val instant = Instant.ofEpochMilli(state.selectedDateMillis ?: Instant.now().toEpochMilli())
      val selectedLocalDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

      SmallButton(
        buttonData = SmallButtonData.Tertiary(
          text = "Wybierz",
          onClick = {
            data.onNewDatePicked(selectedLocalDate)
          },
        )
      )
    },
    colors = datePickerColors,
    content = {
      DatePicker(
        state = state,
        dateFormatter = dateFormatter,
        title = {
          Column(
            modifier = Modifier
              .padding(horizontal = 20.dp)
              .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            Spacer(modifier = Modifier.height(20.dp))
            CustomText(
              text = data.pickerTitle,
              color = AppColors.white,
              style = MaterialTheme.typography.titleMedium,
            )
          }
        },
        headline = {
          DatePickerDefaults.DatePickerHeadline(
            modifier = Modifier
              .padding(horizontal = 10.dp),
            selectedDateMillis = state.selectedDateMillis,
            displayMode = state.displayMode,
            dateFormatter = dateFormatter,
          )
        },
        colors = datePickerColors,
      )
    },
  )
}

private class CustomDatePickerProvider : PreviewParameterProvider<CustomDatePickerData> {
  override val values: Sequence<CustomDatePickerData> = sequenceOf(
    CustomDatePickerData(
      pickedDate = LocalDateTime.now(ZoneId.systemDefault()),
      pickerTitle = "Wybierz date wylotu",
      onNewDatePicked = {},
      onDismiss = {},
    )
  )
}

@Preview(name = "CustomDatePicker preview")
@Composable
fun CustomDatePickerPreview(
  @PreviewParameter(CustomDatePickerProvider::class) data: CustomDatePickerData,
) {
  CustomDatePicker(data = data)
}