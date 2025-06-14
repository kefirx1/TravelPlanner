package pl.bla.dev.common.security

import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec

interface CryptoManager {
  fun getBaseEncryptCipher(
    cryptography: Cryptography,
    key: SecretKey,
  ): Cipher
  fun getBaseDecryptCipher(
    cryptography: Cryptography,
    key: SecretKey,
  ): Cipher

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
    initialCipher: Cipher? = null,
    cryptography: Cryptography = Cryptography.AES_GCM_NoPadding,
  ): ByteArray?

  fun decryptWithKey(
    data: ByteArray,
    key: SecretKey,
    initialCipher: Cipher? = null,
    cryptography: Cryptography = Cryptography.AES_GCM_NoPadding,
  ): ByteArray?

}

class CryptoManagerImpl(
  private val secretKeyProvider: SecretKeyProvider,
): CryptoManager {

  override fun getBaseEncryptCipher(
    cryptography: Cryptography,
    key: SecretKey,
  ): Cipher =
    getCipher(cryptography = cryptography).apply {
      init(Cipher.ENCRYPT_MODE, key)
    }

  override fun getBaseDecryptCipher(
    cryptography: Cryptography,
    key: SecretKey,
  ): Cipher = getCipher(cryptography = cryptography)

  override fun encryptData(
    data: ByteArray,
    cryptography: Cryptography,
  ): ByteArray? {
    return try {
      val encryptCipher = getCipher(cryptography = cryptography).apply {
        init(Cipher.ENCRYPT_MODE, secretKeyProvider.getKeyStoreSecretKey(cryptography = cryptography))
      }
      val encryptedBytes = encryptCipher.doFinal(data)

      byteArrayOf(encryptCipher.iv.size.toByte()) + encryptCipher.iv + encryptedBytes
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

      val ivSize = data[0].toInt()
      val iv = data.copyOfRange(1, ivSize + 1)
      val data = data.copyOfRange(ivSize + 1, data.size)

      cipher.init(Cipher.DECRYPT_MODE, secretKeyProvider.getKeyStoreSecretKey(cryptography = cryptography), IvParameterSpec(iv))
      cipher.doFinal(data)
    } catch (e: Exception) {
      null
    }
  }


  override fun encryptWithKey(
    data: ByteArray,
    key: SecretKey,
    initialCipher: Cipher?,
    cryptography: Cryptography,
  ): ByteArray? {
    return try {
      val cipher = initialCipher ?: getCipher(cryptography = cryptography).apply {
        init(Cipher.ENCRYPT_MODE, key)
      }

      val encryptedData = cipher.doFinal(data)
      byteArrayOf(cipher.iv.size.toByte()) + cipher.iv + encryptedData
    } catch (e: Exception) {
      null
    }
  }

  override fun decryptWithKey(
    concatenatedData: ByteArray,
    key: SecretKey,
    initialCipher: Cipher?,
    cryptography: Cryptography,
  ): ByteArray? {
    return try {
      val cipher = getCipher(cryptography = cryptography)

      val ivSize = concatenatedData[0].toInt()
      val iv = concatenatedData.copyOfRange(1, ivSize + 1)
      val data = concatenatedData.copyOfRange(ivSize + 1, concatenatedData.size)

      cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(128, iv))

      cipher.doFinal(data)
    } catch (e: Exception) {
      null
    }
  }

  private fun getCipher(cryptography: Cryptography): Cipher =
    Cipher.getInstance(cryptography.getTransformation())

}