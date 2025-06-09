package pl.bla.dev.travelplanner.security

import pl.bla.dev.common.security.data.MasterKeyDataStore
import javax.crypto.SecretKey

internal class MasterKeyCacheDataStore : MasterKeyDataStore {
  var masterKey: SecretKey? = null

  override fun saveKey(secretKey: SecretKey) {
    masterKey = secretKey
  }

  override  fun getKey(): SecretKey? = masterKey

  override fun clearKey() {
    masterKey = null
  }
}