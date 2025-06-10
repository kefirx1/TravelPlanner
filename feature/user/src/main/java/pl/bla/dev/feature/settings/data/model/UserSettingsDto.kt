package pl.bla.dev.feature.settings.data.model

import kotlinx.serialization.SerialName

data class UserSettingsDto(
  @SerialName("userName") val userName: String,
  @SerialName("salt") val salt: String,
  @SerialName("ivDek") val ivDek: String,
)
