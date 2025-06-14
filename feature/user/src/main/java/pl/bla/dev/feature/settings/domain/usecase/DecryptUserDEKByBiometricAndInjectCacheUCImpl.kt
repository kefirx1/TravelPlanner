package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.biometric.BiometricPromptManager
import pl.bla.dev.common.biometric.BiometricResult
import pl.bla.dev.common.core.converters.Base64Coder
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.security.CryptoManager
import pl.bla.dev.common.security.Cryptography
import pl.bla.dev.common.security.MasterKeyProvider
import pl.bla.dev.common.security.SecretKeyProvider
import pl.bla.dev.feature.settings.contract.domain.usecase.DecryptUserDEKByBiometricAndInjectCacheUC
import pl.bla.dev.feature.settings.data.repository.UserRepository
import pl.bla.dev.feature.settings.domain.usecase.SetBiometricDataAuthenticationUCImpl.Companion.BIOMETRIC_KEY_ALIAS
import javax.crypto.spec.SecretKeySpec

class DecryptUserDEKByBiometricAndInjectCacheUCImpl(
  private val userRepository: UserRepository,
  private val cryptoManager: CryptoManager,
  private val secretKeyProvider: SecretKeyProvider,
  private val masterKeyProvider: MasterKeyProvider,
  private val base64Coder: Base64Coder,
  private val biometricManager: BiometricPromptManager,
) : DecryptUserDEKByBiometricAndInjectCacheUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, Unit> {
    try {
      val userSettings = userRepository.getUserSettings()
        ?: return Either.Left(AppError.DefaultError(NullPointerException("UserSettings cannot be null")))

      val kekBiometric = secretKeyProvider.getKeyStoreSecretKey(
        cryptography = Cryptography.AES_CBC_PKCS7,
        authenticationRequired = true,
        keyAlias = BIOMETRIC_KEY_ALIAS,
      )

      val initCipher = cryptoManager.getBaseDecryptCipher(
        cryptography = Cryptography.AES_CBC_PKCS7,
        key = kekBiometric,
        encryptedData = base64Coder.decode(data = userSettings.ivDekBiometric),
      )

      biometricManager.showBiometricPrompt(
        title = "Logowanie biometryczne",
        description = "Przyłóż palec do skanera, aby się zalogować",
        negativeButton = "Anuluj",
        cipher = initCipher,
      ).let { result ->
        when (result) {
          is BiometricResult.AuthenticationSuccess -> {
            val dek = cryptoManager.decryptWithKey(
              data = base64Coder.decode(data = userSettings.ivDekBiometric),
              key = kekBiometric,
              initialCipher = result.cipher,
            ) ?: return Either.Left(AppError.DefaultError(Exception("Decryption failed wrong password")))

            masterKeyProvider.saveDecryptedMasterKey(
              masterKey = SecretKeySpec(dek, Cryptography.AES_GCM_NoPadding.algorithm),
            )

            return Either.Right(Unit)
          }
          else -> {
            //TODO error handling
            return Either.Left(AppError.DefaultError(Exception("Decryption failed wrong password")))
          }
        }
      }
    } catch (e: Exception) {
      return Either.Left(AppError.DefaultError(Exception("Decryption failed wrong password")))
    }
  }
}