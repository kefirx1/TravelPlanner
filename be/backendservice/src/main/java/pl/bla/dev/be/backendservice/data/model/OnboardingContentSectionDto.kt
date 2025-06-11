package pl.bla.dev.be.backendservice.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnboardingContentSectionDto(
  @SerialName("sectionId") val sectionId: Int,
  @SerialName("title") val title: String,
  @SerialName("content") val content: List<OnboardingContentItemDto>
)