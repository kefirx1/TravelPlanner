package pl.bla.dev.feature.travel.presentation

import kotlinx.serialization.Serializable
import pl.bla.dev.common.core.navigation.Destination

sealed interface TravelDestinations : Destination {
  @Serializable
  data object TravelGraph : Destination

  @Serializable
  data object NewTravelVehicle : TravelDestinations

  @Serializable
  data object NewTravelOrigin : TravelDestinations

  @Serializable
  data object NewTravelDestination : TravelDestinations

  @Serializable
  data object NewTravelDate : TravelDestinations

  @Serializable
  data object TravelDetails : TravelDestinations

  @Serializable
  data object TravelDialog : TravelDestinations

  @Serializable
  data object TravelDatePicker : TravelDestinations
}

sealed interface TravelResults {
  data object Close : TravelResults
}