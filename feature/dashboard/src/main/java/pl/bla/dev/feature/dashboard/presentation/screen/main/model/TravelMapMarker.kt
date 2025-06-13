package pl.bla.dev.feature.dashboard.presentation.screen.main.model

data class TravelMapMarker(
  val nameOrigin: String,
  val nameDestination: String,
  val longitudeOrigin: Double,
  val latitudeOrigin: Double,
  val longitudeDestination: Double,
  val latitudeDestination: Double,

)
