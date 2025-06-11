package pl.bla.dev.be.backendservice.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnboardingContentItemDto(
  @SerialName("label") val label: String,
  @SerialName("valueId") val valueId: Int,
)
