package pl.bla.dev.common.security.data

import javax.crypto.SecretKey

interface MasterKeyDataStore {
  fun saveKey(secretKey: SecretKey)
  fun getKey(): SecretKey?

  fun clearKey()
}