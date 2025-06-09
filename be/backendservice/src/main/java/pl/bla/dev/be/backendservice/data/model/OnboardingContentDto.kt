package pl.bla.dev.be.backendservice.data.model

//TODO swagger generate DTO with openApi

data class OnboardingContentDto(
  val content: List<OnboardingContentSectionDto>,
)

data class OnboardingContentSectionDto(
  val sectionId: Int,
  val title: String,
  val content: List<OnboardingContentItemDto>
)

data class OnboardingContentItemDto(
  val label: String,
  val valueId: Int,
)
