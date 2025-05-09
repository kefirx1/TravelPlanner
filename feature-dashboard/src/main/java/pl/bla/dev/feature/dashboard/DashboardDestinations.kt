package pl.bla.dev.feature.dashboard

import kotlinx.serialization.Serializable

sealed interface DashboardDestinations {
  @Serializable
  object DashboardGraph

  @Serializable
  object MainDashboard : DashboardDestinations
}