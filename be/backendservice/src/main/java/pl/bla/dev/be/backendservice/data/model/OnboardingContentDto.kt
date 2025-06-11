package pl.bla.dev.be.backendservice.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//TODO swagger generate DTO with openApi
@Serializable
data class OnboardingContentDto(
  @SerialName("content") val content: List<OnboardingContentSectionDto>,
)