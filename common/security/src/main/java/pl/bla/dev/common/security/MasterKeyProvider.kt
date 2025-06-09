package pl.bla.dev.common.security

import pl.bla.dev.common.security.data.MasterKeyDataStore
import java.security.SecureRandom
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

interface MasterKeyProvider {
  fun generateMasterKey(): SecretKey
  fun getMasterKey(): SecretKey?

  fun saveDecryptedMasterKey(masterKey: SecretKey)
}

internal class MasterKeyProviderImpl(
  private val masterKeyDataStore: MasterKeyDataStore,
): MasterKeyProvider {
  override fun generateMasterKey(): SecretKey {
    val key = ByteArray(Cryptography.AES_GCM_NoPadding.keySize)
    SecureRandom().nextBytes(key)
    val masterKey = SecretKeySpec(key, Cryptography.AES_GCM_NoPadding.algorithm)

    saveDecryptedMasterKey(masterKey = masterKey)

    return masterKey
  }

  override fun saveDecryptedMasterKey(masterKey: SecretKey) {
    masterKeyDataStore.saveKey(secretKey = masterKey)
  }

  override fun getMasterKey(): SecretKey? {
    return masterKeyDataStore.getKey()
  }
}