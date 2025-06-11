package pl.bla.dev.feature.settings.contract.domain.model


data class UserOnboardingPreferences(
  val content: List<UserOnboardingPreferencesSection>,
)
data class UserOnboardingPreferencesSection(
  val sectionId: Int,
  val title: String,
  val content: List<UserOnboardingPreferencesItem>
)
data class UserOnboardingPreferencesItem(
  val label: String,
  val valueId: Int,
)