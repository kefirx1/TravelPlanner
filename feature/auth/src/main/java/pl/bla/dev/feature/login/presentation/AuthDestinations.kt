package pl.bla.dev.feature.login.presentation

import kotlinx.serialization.Serializable
import pl.bla.dev.common.core.navigation.Destination

sealed interface AuthDestinations : Destination {

  @Serializable
  data object AuthGraph : Destination

  @Serializable
  data object Login : AuthDestinations

  @Serializable
  data object Registration : AuthDestinations

  @Serializable
  data object Onboarding : AuthDestinations
}

sealed interface AuthResults {
  data object LoginSuccess : AuthResults
  data object RegistrationSuccess : AuthResults
  data object ExitApp : AuthResults
}