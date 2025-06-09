package pl.bla.dev.be.backendservice.contract.domain.model

data class OnboardingContent(
  val content: List<OnboardingContentSection>,
)

data class OnboardingContentSection(
  val sectionId: Int,
  val title: String,
  val content: List<OnboardingContentItem>
)

data class OnboardingContentItem(
  val label: String,
  val valueId: Int,
)
