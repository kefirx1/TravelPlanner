package pl.bla.dev.travelplanner.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import pl.bla.dev.common.security.CryptoManager
import pl.bla.dev.common.security.Cryptography
import pl.bla.dev.common.storage.datastore.DataStoreProvider
import java.util.Base64

internal class DataStoreProviderImpl(
  private val cryptoManager: CryptoManager,
  private val context: Context,
  private val gson: Gson,
): DataStoreProvider {

  companion object {
    private const val APP_DATA_STORE_PREFS_NAME = "app_data_store_prefs"
  }

  val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = APP_DATA_STORE_PREFS_NAME)

  override suspend fun <T> getDataStoreData(dataStoreKey: String): T? {
    return try {
      getDataStore().data.first().let { prefs ->
        gson.fromJson<T>(prefs.get(key = stringPreferencesKey(name = dataStoreKey)), object : TypeToken<T>(){}.type)
      }
    } catch (e: Exception) {
      null
    }
  }

  override suspend fun <T> getDataStoreDataFlow(
    dataStoreKey: String
  ): Flow<T>? {
    return try {
       getDataStore().data.mapNotNull { prefs ->
        (prefs.get(key = stringPreferencesKey(dataStoreKey)) ?: return@mapNotNull null).let { data ->
          val decodedData = Base64.getDecoder().decode(data)
          val decryptedData = cryptoManager.decryptData(
            data = decodedData,
            cryptography = Cryptography.AES_CBC_PKCS7,
          )?.decodeToString()

          gson.fromJson<T>(decryptedData, object : TypeToken<T>(){}.type)
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
        data = gson.toJson(data).toByteArray(),
        cryptography = Cryptography.AES_CBC_PKCS7,
      )

      getDataStore().edit { prefs ->
        prefs[stringPreferencesKey(name = dataStoreKey)] =
          Base64.getEncoder().encodeToString(encryptedData) //TODO Base64 to security module
      }
    } catch (e: Exception) {

    }
  }

  private fun getDataStore(): DataStore<Preferences> = context.dataStore
}