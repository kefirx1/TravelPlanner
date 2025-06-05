package pl.bla.dev.feature.dashboard.presentation

import kotlinx.serialization.Serializable
import pl.bla.dev.common.core.navigation.Destination

sealed interface DashboardDestinations : Destination {
  @Serializable
  object DashboardGraph

  @Serializable
  object MainDashboard : DashboardDestinations

  @Serializable
  data class DashboardDialog(
    val type: Types,
  ) : DashboardDestinations {
    enum class Types {
      LOGOUT_DIALOG
    }
  }
}

sealed interface DashboardResults {
  data object Logout : DashboardResults
}