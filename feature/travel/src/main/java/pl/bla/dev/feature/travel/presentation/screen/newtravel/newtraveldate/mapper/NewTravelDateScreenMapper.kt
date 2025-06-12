package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.picker.CustomDatePickerData
import pl.bla.dev.common.ui.componenst.picker.DatePickerInputData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.NewTravelDateVM
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface NewTravelDateScreenMapper : Mapper<NewTravelDateScreenMapper.Params, NewTravelDateVM.ScreenData> {
  data class Params(
    val state: NewTravelDateVM.State,
    val onBackClick: () -> Unit,
    val onCloseProcessClick: () -> Unit,
    val onAddNewClick: () -> Unit,
    val onStartDateSelect: (LocalDateTime) -> Unit,
    val onEndDateSelect: (LocalDateTime) -> Unit,
    val onShowDatePickerClick: (CustomDatePickerData) -> Unit,
  ): UseCase.Params
}

internal class NewTravelDateScreenMapperImpl : NewTravelDateScreenMapper {
  override fun invoke(params: NewTravelDateScreenMapper.Params): NewTravelDateVM.ScreenData =
    when (params.state) {
      is NewTravelDateVM.State.Initialized -> NewTravelDateVM.ScreenData(
        topAppBarData = TopAppBarData.BackAndAction(
          onNavigationIconClick = params.onBackClick,
          onActionIconClick = params.onCloseProcessClick,
        ),
        onBackClick = params.onBackClick,
        addNewButtonData = LargeButtonData.Primary(
          text = "Dodaj podróż",
          onClick = params.onAddNewClick,
        ),
        startDateLabel = "Data rozpoczęcia wyjazdu",
        endDateLabel = "Data zakończenia wyjazdu",
        startDatePickerInputData = DatePickerInputData(
          pickedDate = params.state.startDate.getFormattedDate(),
          onClick = {
            params.onShowDatePickerClick(
              CustomDatePickerData(
                pickerTitle = "Wybierz date rozpoczęcia",
                pickedDate = params.state.startDate,
                onNewDatePicked = { newDate ->
                  params.onStartDateSelect(newDate)
                },
                onDismiss = {},
              )
            )
          },
        ),
        endDatePickerInputData = DatePickerInputData(
          pickedDate = params.state.endDate.getFormattedDate(),
          onClick = {
            params.onShowDatePickerClick(
              CustomDatePickerData(
                pickerTitle = "Wybierz date zakończenia",
                pickedDate = params.state.endDate,
                onNewDatePicked = { newDate ->
                  params.onEndDateSelect(newDate)
                },
                onDismiss = {},
              )
            )
          },
        ),
      )
    }

  private fun LocalDateTime.getFormattedDate(): String =
    this.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

}