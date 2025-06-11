package pl.bla.dev.feature.settings.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.bla.dev.feature.settings.data.dao.UserInfoDao
import pl.bla.dev.feature.settings.data.dao.UserTravelsDao
import pl.bla.dev.feature.settings.data.model.LocalDateTimeConverter
import pl.bla.dev.feature.settings.data.model.OnboardingPreferencesConverter
import pl.bla.dev.feature.settings.data.model.UserInfoDto
import pl.bla.dev.feature.settings.data.model.UserTravelsDto

@Database(version = 1, entities = [UserInfoDto::class, UserTravelsDto::class])
@TypeConverters(OnboardingPreferencesConverter::class, LocalDateTimeConverter::class)
abstract class UserDatabase : RoomDatabase() {
  abstract fun userInfoDao(): UserInfoDao
  abstract fun userTravelsDao(): UserTravelsDao

  companion object {
    const val USER_DATABASE_NAME = "user-database"
  }
}