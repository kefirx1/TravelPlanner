package pl.bla.dev.travelplanner.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import pl.bla.dev.common.core.converters.Base64Coder
import pl.bla.dev.common.core.converters.JsonSerializer
import pl.bla.dev.common.security.CryptoManager
import pl.bla.dev.common.security.Cryptography
import pl.bla.dev.common.storage.datastore.DataStoreProvider
import java.lang.reflect.Type

internal class DataStoreProviderImpl(
  private val cryptoManager: CryptoManager,
  private val context: Context,
  private val jsonSerializer: JsonSerializer,
  private val base64Coder: Base64Coder,
): DataStoreProvider {

  companion object {
    private const val APP_DATA_STORE_PREFS_NAME = "app_data_store_prefs"
  }

  val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = APP_DATA_STORE_PREFS_NAME)

  override suspend fun <T> getDataStoreData(dataStoreKey: String, type: Type): T? {
    return try {
      getDataStore().data.first().let { prefs ->
        (prefs.get(key = stringPreferencesKey(dataStoreKey)) ?: return null).let { data ->
          val decodedData = base64Coder.decode(data = data)
          val decryptedData = cryptoManager.decryptData(
            data = decodedData,
            cryptography = Cryptography.AES_CBC_PKCS7,
          )?.decodeToString()

          jsonSerializer.deserialize(serializedData = decryptedData, type = type)
        }
      }
    } catch (e: Exception) {
      null
    }
  }

  override suspend fun <T> getDataStoreDataFlow(
    dataStoreKey: String,
    type: Type,
  ): Flow<T>? {
    return try {
       getDataStore().data.mapNotNull { prefs ->
        (prefs.get(key = stringPreferencesKey(dataStoreKey)) ?: return@mapNotNull null).let { data ->
          val decodedData = base64Coder.decode(data = data)
          val decryptedData = cryptoManager.decryptData(
            data = decodedData,
            cryptography = Cryptography.AES_CBC_PKCS7,
          )?.decodeToString()

          jsonSerializer.deserialize(serializedData = decryptedData, type = type)
        }
      }
    } catch (e: Exception) {
      null
    }
  }

  override suspend fun <T> updateDataStoreData(
    dataStoreKey: String,
    data: T
  ) {
    try {
      val encryptedData = cryptoManager.encryptData(
        data = jsonSerializer.serialize(data = data).toByteArray(),
        cryptography = Cryptography.AES_CBC_PKCS7,
      )

      getDataStore().edit { prefs ->
        prefs[stringPreferencesKey(name = dataStoreKey)] =
          base64Coder.encode(data = encryptedData)
      }
    } catch (e: Exception) {

    }
  }

  private fun getDataStore(): DataStore<Preferences> = context.dataStore
}