package pl.bla.dev.feature.settings.contract.domain.model

data class UserInfo(
  val uid: Int,
  val firstName: String,
  val sureName: String,
  val email: String,
  val onboardingPreferences: UserOnboardingPreferences,
)