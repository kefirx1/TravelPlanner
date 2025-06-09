package pl.bla.dev.feature.settings.data.repository

import pl.bla.dev.common.security.MasterKeyProvider
import pl.bla.dev.common.storage.room.DatabaseProvider
import pl.bla.dev.feature.settings.contract.domain.model.UserSettings
import pl.bla.dev.feature.settings.data.model.UserInfo
import pl.bla.dev.feature.settings.data.source.UserDatabase
import pl.bla.dev.feature.settings.data.source.UserSettingsDataStore

interface UserRepository {
  suspend fun registerNewUser(userInfo: UserInfo)

  suspend fun saveNewUserSettings(userSettings: UserSettings)
  suspend fun getUserSettings(): UserSettings?
}

internal class UserRepositoryImpl(
  val userSettingsDataStore: UserSettingsDataStore,
  val databaseProvider: DatabaseProvider,
  masterKeyProvider: MasterKeyProvider,
) : UserRepository {

  private val userDatabase: UserDatabase by lazy {
    databaseProvider.buildDatabase(
      databaseName = UserDatabase.USER_DATABASE_NAME,
      databaseClass = UserDatabase::class.java,
      masterKey = masterKeyProvider.getMasterKey() ?: throw NullPointerException("Master key cannot be null")
    )
  }

  override suspend fun saveNewUserSettings(userSettings: UserSettings) {
    userSettingsDataStore.save(newSettings = userSettings)
  }

  override suspend fun getUserSettings(): UserSettings? =
    userSettingsDataStore.load()


  override suspend fun registerNewUser(userInfo: UserInfo) {
    userDatabase.userInfoDao()
  }



}