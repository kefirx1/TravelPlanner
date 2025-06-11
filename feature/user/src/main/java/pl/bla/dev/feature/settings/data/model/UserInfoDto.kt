package pl.bla.dev.feature.settings.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfoDto(
  @PrimaryKey(autoGenerate = true) val uid: Int = 0,
  @ColumnInfo(name = "first_name") val firstName: String,
  @ColumnInfo(name = "sure_name") val sureName: String = "",
  @ColumnInfo(name = "email") val email: String,
  @ColumnInfo(name = "onboarding_preferences") val onboardingPreferences: UserOnboardingPreferencesDto,
)