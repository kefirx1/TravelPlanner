package pl.bla.dev.common.security

import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec

interface CryptoManager {
  fun encryptData(
    data: ByteArray,
    cryptography: Cryptography,
  ): ByteArray?

  fun decryptData(
    data: ByteArray,
    cryptography: Cryptography,
  ): ByteArray?

  fun encryptWithKey(
    data: ByteArray,
    key: SecretKey,
  ): ByteArray?

  fun decryptWithKey(
    data: ByteArray,
    key: SecretKey,
  ): ByteArray?

}

class CryptoManagerImpl(
  private val secretKeyProvider: SecretKeyProvider,
): CryptoManager {

  override fun encryptData(
    data: ByteArray,
    cryptography: Cryptography,
  ): ByteArray? {
    return try {
      val encryptCipher = getCipher(cryptography = cryptography).apply {
        init(Cipher.ENCRYPT_MODE, secretKeyProvider.getKeyStoreSecretKey(cryptography = cryptography))
      }
      val encryptedBytes = encryptCipher.doFinal(data)

      encryptCipher.iv + encryptedBytes
    } catch (e: Exception) {
      null
    }
  }

  override fun decryptData(
    data: ByteArray,
    cryptography: Cryptography,
  ): ByteArray? {
    return try {
      val cipher = getCipher(cryptography = cryptography)

      val iv = data.copyOfRange(0, cipher.blockSize)
      val data = data.copyOfRange(cipher.blockSize, data.size)

      cipher.init(Cipher.DECRYPT_MODE, secretKeyProvider.getKeyStoreSecretKey(cryptography = cryptography), IvParameterSpec(iv))
      cipher.doFinal(data)
    } catch (e: Exception) {
      null
    }
  }


  override fun encryptWithKey(
    data: ByteArray,
    key: SecretKey,
  ): ByteArray? {
    return try {
      val cipher = getCipher(cryptography = Cryptography.AES_GCM_NoPadding).apply {
        init(Cipher.ENCRYPT_MODE, key)
      }

      val encryptedData = cipher.doFinal(data)
      cipher.iv + encryptedData
    } catch (e: Exception) {
      null
    }
  }

  override fun decryptWithKey(
    concatenatedData: ByteArray,
    key: SecretKey,
  ): ByteArray? {
    return try {
      val cipher = getCipher(cryptography = Cryptography.AES_GCM_NoPadding)

      val iv = concatenatedData.copyOfRange(0, cipher.blockSize)
      val data = concatenatedData.copyOfRange(cipher.blockSize, concatenatedData.size)

      cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(128, iv))

      cipher.doFinal(data)
    } catch (e: Exception) {
      null
    }
  }

  private fun getCipher(cryptography: Cryptography): Cipher =
    Cipher.getInstance(cryptography.getTransformation())

}