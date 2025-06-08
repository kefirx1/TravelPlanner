package pl.bla.dev.common.storage.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreProvider {
  suspend fun <T> getDataStoreData(dataStoreKey: String): T?

  suspend fun <T> getDataStoreDataFlow(dataStoreKey: String): Flow<T>?

  suspend fun <T> updateDataStoreData(dataStoreKey: String, data: T)
}