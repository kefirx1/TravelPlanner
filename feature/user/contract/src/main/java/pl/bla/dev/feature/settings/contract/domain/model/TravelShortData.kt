package pl.bla.dev.feature.settings.contract.domain.model

import pl.bla.dev.be.backendservice.contract.domain.model.VehicleType
import java.time.LocalDateTime

data class TravelShortData(
  val travelId: Int,
  val originCity: String,
  val originCountry: String,
  val destinationCity: String,
  val destinationCountry: String,
  val startDate: LocalDateTime,
  val endDate: LocalDateTime,
  val travelStatus: TravelStatus,
  val originVehicleType: VehicleType,
  val destinationVehicleType: VehicleType,
  val longitudeOrigin: Double,
  val latitudeOrigin: Double,
  val longitudeDestination: Double,
  val latitudeDestination: Double,
)