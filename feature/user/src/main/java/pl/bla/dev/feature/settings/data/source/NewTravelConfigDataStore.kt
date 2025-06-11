package pl.bla.dev.feature.settings.data.source

import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.common.storage.datastore.DataStoreProvider
import pl.bla.dev.feature.settings.data.model.NewTravelConfigDto
import pl.bla.dev.feature.settings.domain.mapper.UserMapper.toDomain
import pl.bla.dev.feature.settings.domain.mapper.UserMapper.toDto


interface NewTravelConfigDataStore {
  suspend fun load(): NewTravelConfig?
  suspend fun save(newConfig: NewTravelConfig)
}

internal class NewTravelConfigPreferencesDataStore(
  private val dataStoreProvider: DataStoreProvider,
): NewTravelConfigDataStore {

  companion object {
    private const val STORE_NAME = "NEW_TRAVEL_CONFIG"
  }

  override suspend fun save(newConfig: NewTravelConfig) {
    dataStoreProvider.updateDataStoreData(
      dataStoreKey = STORE_NAME,
      data = newConfig.toDto(),
    )
  }

  override suspend fun load(): NewTravelConfig? =
    dataStoreProvider.getDataStoreData<NewTravelConfigDto>(
      dataStoreKey = STORE_NAME,
      type = NewTravelConfigDto::class.java,
    )?.toDomain()

}