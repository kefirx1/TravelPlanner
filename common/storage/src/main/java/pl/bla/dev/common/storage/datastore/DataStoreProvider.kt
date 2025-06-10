package pl.bla.dev.common.storage.datastore

import kotlinx.coroutines.flow.Flow
import java.lang.reflect.Type

interface DataStoreProvider {
  suspend fun <T> getDataStoreData(dataStoreKey: String, type: Type): T?

  suspend fun <T> getDataStoreDataFlow(dataStoreKey: String, type: Type): Flow<T>?

  suspend fun <T> updateDataStoreData(dataStoreKey: String, data: T)
}