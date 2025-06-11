package pl.bla.dev.feature.settings.contract.domain.model

import java.time.LocalDateTime

data class UserTravels(
  val uid: Int,
  val userId: Int,
  val originCountryId: Int,
  val destinationCountryId: Int,
  val originCityId: Int,
  val destinationCityId: Int,
  val originVehicleId: Int,
  val destinationVehicleId: Int,
  val cancelled: Boolean,
  val startDate: LocalDateTime,
  val endDate: LocalDateTime,
)
