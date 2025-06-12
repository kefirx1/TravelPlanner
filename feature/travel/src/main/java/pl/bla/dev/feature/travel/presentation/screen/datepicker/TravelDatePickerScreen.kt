package pl.bla.dev.feature.travel.presentation.screen.datepicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.common.ui.componenst.picker.CustomDatePicker

@Composable
fun TravelDatePickerScreen(viewModel: TravelDatePickerVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()

  CustomDatePicker(data = state.customDatePickerData)
}