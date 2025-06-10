package pl.bla.dev.be.backendservice.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

//TODO swagger generate DTO with openApi
@Serializable
data class OnboardingContentDto(
  @SerialName("content") val content: List<OnboardingContentSectionDto>,
)

@Serializable
data class OnboardingContentSectionDto(
  @SerialName("sectionId") val sectionId: Int,
  @SerialName("title") val title: String,
  @SerialName("content") val content: List<OnboardingContentItemDto>
)

@Serializable
data class OnboardingContentItemDto(
  @SerialName("label") val label: String,
  @SerialName("valueId") val valueId: Int,
)
