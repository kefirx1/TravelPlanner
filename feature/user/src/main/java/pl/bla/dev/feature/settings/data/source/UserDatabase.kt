package pl.bla.dev.feature.settings.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.bla.dev.feature.settings.data.dao.UserInfoDao
import pl.bla.dev.feature.settings.data.model.UserInfo

@Database(version = 1, entities = [UserInfo::class])
abstract class UserDatabase : RoomDatabase() {
  abstract fun userInfoDao(): UserInfoDao

  companion object {
    const val USER_DATABASE_NAME = "user-database"
  }
}