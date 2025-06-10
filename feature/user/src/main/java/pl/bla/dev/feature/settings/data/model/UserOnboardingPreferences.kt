package pl.bla.dev.feature.settings.data.model

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import pl.bla.dev.common.core.converters.JsonSerializer


@Serializable
data class UserOnboardingPreferences(
  @SerialName(value = "content") val content: List<UserOnboardingPreferencesSection>,
)
@Serializable
data class UserOnboardingPreferencesSection(
  @SerialName(value = "sectionId") val sectionId: Int,
  @SerialName(value = "title") val title: String,
  @SerialName(value = "content") val content: List<UserOnboardingPreferencesItem>
)
@Serializable
data class UserOnboardingPreferencesItem(
  @SerialName(value = "label") val label: String,
  @SerialName(value = "valueId") val valueId: Int,
)

@ProvidedTypeConverter
class OnboardingPreferencesConverter(
  private val jsonSerializer: JsonSerializer,
) {
  @TypeConverter
  fun fromUserOnboardingPreferences(preferences: UserOnboardingPreferences?): String? {
    if (preferences == null) {
      return null
    }
    return jsonSerializer.serialize(data = preferences)
  }

  @TypeConverter
  fun toUserOnboardingPreferences(json: String?): UserOnboardingPreferences? {
    if (json == null) {
      return null
    }
    return jsonSerializer.deserialize(serializedData = json, type = UserOnboardingPreferences::class.java)
  }
}