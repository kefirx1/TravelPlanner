package pl.bla.dev.feature.settings.data.model

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import pl.bla.dev.common.core.converters.JsonSerializer


@Serializable
data class UserOnboardingPreferencesDto(
  @SerialName(value = "content") val content: List<UserOnboardingPreferencesSectionDto>,
)
@Serializable
data class UserOnboardingPreferencesSectionDto(
  @SerialName(value = "sectionId") val sectionId: Int,
  @SerialName(value = "title") val title: String,
  @SerialName(value = "content") val content: List<UserOnboardingPreferencesItemDto>
)
@Serializable
data class UserOnboardingPreferencesItemDto(
  @SerialName(value = "label") val label: String,
  @SerialName(value = "valueId") val valueId: Int,
)

@ProvidedTypeConverter
class OnboardingPreferencesConverter(
  private val jsonSerializer: JsonSerializer,
) {
  @TypeConverter
  fun fromUserOnboardingPreferences(preferences: UserOnboardingPreferencesDto?): String? {
    if (preferences == null) {
      return null
    }
    return jsonSerializer.serialize(data = preferences)
  }

  @TypeConverter
  fun toUserOnboardingPreferences(json: String?): UserOnboardingPreferencesDto? {
    if (json == null) {
      return null
    }
    return jsonSerializer.deserialize(serializedData = json, type = UserOnboardingPreferencesDto::class.java)
  }
}