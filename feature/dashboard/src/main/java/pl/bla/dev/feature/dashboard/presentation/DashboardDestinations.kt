package pl.bla.dev.feature.dashboard.presentation

import kotlinx.serialization.Serializable
import pl.bla.dev.common.core.navigation.Destination

sealed interface DashboardDestinations : Destination {
  @Serializable
  data object DashboardGraph : Destination

  @Serializable
  data object MainDashboard : DashboardDestinations

  @Serializable
  data object DashboardDialog : DashboardDestinations
}

sealed interface DashboardResults {
  data object Logout : DashboardResults
  data class ToTravelDetails(val travelId: Int) : DashboardResults
  data object ToNewTravel : DashboardResults
}