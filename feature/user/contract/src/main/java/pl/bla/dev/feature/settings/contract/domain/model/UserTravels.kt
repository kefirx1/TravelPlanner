package pl.bla.dev.feature.settings.contract.domain.model

import pl.bla.dev.be.backendservice.contract.domain.model.VehicleType
import java.time.LocalDateTime

data class UserTravels(
  val uid: Int = 0,
  val userId: Int = 0,
  val cancelled: Boolean,
  val originContinentId: Int,
  val destinationContinentId: Int,
  val originCityId: Int,
  val originCity: String,
  val originCountryId: Int,
  val originCountry: String,
  val destinationCityId: Int,
  val destinationCity: String,
  val destinationCountryId: Int,
  val destinationCountry: String,
  val startDate: LocalDateTime,
  val endDate: LocalDateTime,
  val originVehicleId: Int,
  val originVehicleName: String,
  val originVehicleDescription: String,
  val originVehicleAddress: String,
  val originVehicleLatitude: Double,
  val originVehicleLongitude: Double,
  val originVehicleType: VehicleType,
  val destinationVehicleId: Int,
  val destinationVehicleName: String,
  val destinationVehicleDescription: String,
  val destinationVehicleAddress: String,
  val destinationVehicleLatitude: Double,
  val destinationVehicleLongitude: Double,
  val destinationVehicleType: VehicleType,
)
