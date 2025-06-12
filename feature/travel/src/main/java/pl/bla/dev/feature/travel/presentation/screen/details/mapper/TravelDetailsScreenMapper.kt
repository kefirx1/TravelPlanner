package pl.bla.dev.feature.travel.presentation.screen.details.mapper

import pl.bla.dev.be.backendservice.contract.domain.model.VehicleType
import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.ui.componenst.button.LargeButton
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.settings.contract.domain.model.TravelStatus
import pl.bla.dev.feature.travel.presentation.screen.details.TravelDetailsVM
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface TravelDetailsScreenMapper : Mapper<TravelDetailsScreenMapper.Params, TravelDetailsVM.ScreenData> {
  data class Params(
    val state: TravelDetailsVM.State,
    val onBackClick: () -> Unit,
    val onRemoveClick: () -> Unit,
    val onCancelClick: () -> Unit,
    val onRestoreClick: () -> Unit,
  )
}

internal class TravelDetailsScreenMapperImpl : TravelDetailsScreenMapper {
  override fun invoke(params: TravelDetailsScreenMapper.Params): TravelDetailsVM.ScreenData =
    when (params.state) {
      is TravelDetailsVM.State.Initial -> TravelDetailsVM.ScreenData.Initial(
        onBackClick = params.onBackClick,
      )
      is TravelDetailsVM.State.Initialized -> TravelDetailsVM.ScreenData.Initialized(
        topAppBarData = TopAppBarData.BackAndTitle(
          title = when (params.state.travelDetails.travelStatus) {
            TravelStatus.PAST -> "Wycieczka zakończona"
            TravelStatus.CURRENT -> "Wycieczka w trakcie"
            TravelStatus.FUTURE -> "Przyszła wycieczka"
            TravelStatus.CANCELLED -> "Wycieczka odwołana"
          },
          onNavigationIconClick = params.onBackClick,
        ),
        onBackClick = params.onBackClick,
        originSection = "Start",
        destinationSection = "Powrót",
        travelStartDate = "Data ${params.state.travelDetails.originVehicleType.getDatePrefix()}: ${params.state.travelDetails.startDate.getDate()}",
        travelOrigin = "${params.state.travelDetails.originVehicleType.getOriginPrefix()} z ${params.state.travelDetails.originCity} (${params.state.travelDetails.originCountry})",
        travelDestination = "${params.state.travelDetails.originVehicleType.getDestinationPrefix()} do ${params.state.travelDetails.destinationCity} (${params.state.travelDetails.destinationCountry})",

        travelEndDate = "Data ${params.state.travelDetails.destinationVehicleType.getDatePrefix()}: ${params.state.travelDetails.endDate.getDate()}",
        travelOriginReturn = "${params.state.travelDetails.destinationVehicleType.getOriginPrefix()} z ${params.state.travelDetails.destinationCity} (${params.state.travelDetails.destinationCountry})",
        travelDestinationReturn = "${params.state.travelDetails.destinationVehicleType.getDestinationPrefix()} do ${params.state.travelDetails.originCity} (${params.state.travelDetails.originCountry})",

        travelOriginVehicleName = params.state.travelDetails.originVehicleName,
        travelOriginVehicleAddress = params.state.travelDetails.originVehicleAddress,
        travelDestinationVehicleName = params.state.travelDetails.destinationVehicleName,
        travelDestinationVehicleAddress = params.state.travelDetails.destinationVehicleAddress,
        cancelTravelButtonData = when (params.state.travelDetails.travelStatus) {
          TravelStatus.FUTURE -> LargeButtonData.Secondary(
            text = "Odwołaj wycieczkę",
            onClick = params.onCancelClick,
          )
          TravelStatus.CANCELLED -> LargeButtonData.Secondary(
            text = "Przywróć wycieczkę",
            onClick = params.onRestoreClick,
          )
          else -> null
        },
        removeTravelButtonData = LargeButtonData.Tertiary(
          text = "Usuń wycieczkę",
          onClick = params.onRemoveClick,
        )
      )
    }

  private fun VehicleType.getDestinationPrefix() = when (this) {
    VehicleType.CAR -> "Przyjazd"
    VehicleType.BUS -> "Przyjazd"
    VehicleType.TRAIN -> "Przyjazd"
    VehicleType.PLANE -> "Przylot"
  }

  private fun VehicleType.getOriginPrefix() = when (this) {
    VehicleType.CAR -> "Wyjazd"
    VehicleType.BUS -> "Wyjazd"
    VehicleType.TRAIN -> "Odjazd"
    VehicleType.PLANE -> "Wylot"
  }

  private fun VehicleType.getDatePrefix() = when (this) {
    VehicleType.CAR -> "wyjazdu"
    VehicleType.BUS -> "wyjazu"
    VehicleType.TRAIN -> "odjazdu"
    VehicleType.PLANE -> "wylotu"
  }

  private fun LocalDateTime.getDate() = this.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
}