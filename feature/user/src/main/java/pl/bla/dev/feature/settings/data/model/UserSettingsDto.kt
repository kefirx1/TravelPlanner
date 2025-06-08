package pl.bla.dev.feature.settings.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserSettingsDto(
  @SerializedName("userName") val userName: String,
)
