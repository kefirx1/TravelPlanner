package pl.bla.dev.common.security

import android.security.keystore.KeyProperties

sealed class Cryptography(
  val algorithm: String,
  val blockMode: String,
  val padding: String
) {
  data object AES: Cryptography(
    algorithm = KeyProperties.KEY_ALGORITHM_AES,
    blockMode = KeyProperties.BLOCK_MODE_CBC,
    padding = KeyProperties.ENCRYPTION_PADDING_PKCS7
  )
  data object RSA: Cryptography(
    algorithm = KeyProperties.KEY_ALGORITHM_RSA,
    blockMode = KeyProperties.BLOCK_MODE_CBC,
    padding = KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1,
  )

  fun getTransformation(): String =
    "$algorithm/$blockMode/$padding"
}