package pl.bla.dev.common.security

import pl.bla.dev.common.security.Cryptography
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

interface CryptoManager {
  fun encryptData(
    data: ByteArray,
    cryptography: Cryptography,
  ): ByteArray

  fun decryptData(
    data: ByteArray,
    cryptography: Cryptography,
  ): ByteArray
}

class CryptoManagerImpl(
  private val keyStoreProvider: KeyStoreProvider,
): CryptoManager {

  override fun encryptData(data: ByteArray, cryptography: Cryptography): ByteArray {
    val encryptCipher = getCipher(cryptography = cryptography).apply {
      init(Cipher.ENCRYPT_MODE, keyStoreProvider.getSecurityKey(cryptography = cryptography))
    }
    val encryptedBytes = encryptCipher.doFinal(data)

    return encryptCipher.iv + encryptedBytes
  }


  override fun decryptData(data: ByteArray, cryptography: Cryptography): ByteArray {
    val cipher = getCipher(cryptography = cryptography)

    val iv = data.copyOfRange(0, cipher.blockSize)
    val data = data.copyOfRange(cipher.blockSize, data.size)

    cipher.init(Cipher.DECRYPT_MODE, keyStoreProvider.getSecurityKey(cryptography = cryptography), IvParameterSpec(iv))
    return cipher.doFinal(data)
  }

  private fun getCipher(cryptography: Cryptography): Cipher =
    Cipher.getInstance(cryptography.getTransformation())

}