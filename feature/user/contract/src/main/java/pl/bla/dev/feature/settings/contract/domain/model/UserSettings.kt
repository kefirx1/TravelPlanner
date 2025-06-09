package pl.bla.dev.feature.settings.contract.domain.model

data class UserSettings(
  val userName: String,
  val salt: ByteArray,
  val ivDek: ByteArray,
)