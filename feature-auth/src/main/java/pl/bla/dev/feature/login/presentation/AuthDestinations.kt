package pl.bla.dev.feature.login.presentation

import kotlinx.serialization.Serializable

sealed interface AuthDestinations {
  @Serializable
  data object AuthGraph

  @Serializable
  data object Login : AuthDestinations
}