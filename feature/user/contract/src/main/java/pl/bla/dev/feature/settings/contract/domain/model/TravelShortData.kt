package pl.bla.dev.feature.settings.contract.domain.model

import java.time.LocalDateTime

data class TravelShortData(
  val id: String,
  val origin: String,
  val originCountry: String,
  val destination: String,
  val destinationCountry: String,
  val date: LocalDateTime,
  val travelStatus: TravelStatus,
  val locomotionType: LocomotionType,
)