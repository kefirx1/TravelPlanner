package pl.bla.dev.common.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import pl.bla.dev.common.security.Cryptography
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

interface KeyStoreProvider {
  fun getSecurityKey(cryptography: Cryptography): SecretKey
}

internal class KeyStoreProviderImpl(): KeyStoreProvider {

  companion object {
    private const val ANDROID_KEYSTORE = "AndroidKeyStore"
    private const val KEY_ALIAS = "keyAlias"
  }

  private val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply {
    load(null)
  }

  override fun getSecurityKey(cryptography: Cryptography): SecretKey {
    val key = keyStore.getEntry(KEY_ALIAS, null) as? KeyStore.SecretKeyEntry

    return key?.secretKey ?: generateKey(cryptography = cryptography)
  }

  private fun generateKey(cryptography: Cryptography): SecretKey {
    return KeyGenerator.getInstance(cryptography.algorithm).apply {
      init(
        KeyGenParameterSpec.Builder(KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
          .setBlockModes(cryptography.blockMode)
          .setEncryptionPaddings(cryptography.padding)
          .setUserAuthenticationRequired(false)
          .setInvalidatedByBiometricEnrollment(false)
          .setRandomizedEncryptionRequired(true)
          .build()
      )
    }.generateKey()
  }
}