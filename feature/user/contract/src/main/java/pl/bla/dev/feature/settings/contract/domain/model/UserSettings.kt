package pl.bla.dev.feature.settings.contract.domain.model

data class UserSettings(
  val userName: String,
  val salt: String,
  val ivDek: String,
  val ivDekBiometric: String?,
)