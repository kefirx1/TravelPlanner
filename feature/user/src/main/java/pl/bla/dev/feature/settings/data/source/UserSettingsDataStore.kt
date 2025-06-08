package pl.bla.dev.feature.settings.data.source

import pl.bla.dev.common.storage.datastore.DataStoreProvider
import pl.bla.dev.feature.settings.contract.domain.model.UserSettings
import pl.bla.dev.feature.settings.data.model.UserSettingsDto
import pl.bla.dev.feature.settings.domain.mapper.UserMapper.toDomain
import pl.bla.dev.feature.settings.domain.mapper.UserMapper.toDto


interface UserSettingsDataStore {
  suspend fun load(): UserSettings?
  suspend fun save(newSettings: UserSettings)
}

internal class UserSettingsPreferencesDataStore(
  private val dataStoreProvider: DataStoreProvider,
): UserSettingsDataStore {

  companion object {
    private const val STORE_NAME = "USER_SETTINGS"
  }

  override suspend fun save(newSettings: UserSettings) {
    dataStoreProvider.updateDataStoreData(
      dataStoreKey = STORE_NAME,
      data = newSettings.toDto(),
    )
  }

  override suspend fun load(): UserSettings? =
    dataStoreProvider.getDataStoreData<UserSettingsDto>(
      dataStoreKey = STORE_NAME,
    )?.toDomain()

}