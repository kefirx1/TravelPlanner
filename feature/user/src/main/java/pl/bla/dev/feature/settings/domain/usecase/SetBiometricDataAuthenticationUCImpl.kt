package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.biometric.BiometricPromptManager
import pl.bla.dev.common.biometric.BiometricResult
import pl.bla.dev.common.core.converters.Base64Coder
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.common.security.CryptoManager
import pl.bla.dev.common.security.Cryptography
import pl.bla.dev.common.security.MasterKeyProvider
import pl.bla.dev.common.security.SecretKeyProvider
import pl.bla.dev.feature.settings.contract.domain.usecase.SetBiometricDataAuthenticationUC
import pl.bla.dev.feature.settings.data.repository.UserRepository

class SetBiometricDataAuthenticationUCImpl(
  private val masterKeyProvider: MasterKeyProvider,
  private val secretKeyProvider: SecretKeyProvider,
  private val cryptoManager: CryptoManager,
  private val userRepository: UserRepository,
  private val base64Coder: Base64Coder,
  private val biometricManager: BiometricPromptManager,
) : SetBiometricDataAuthenticationUC {

  companion object {
    const val BIOMETRIC_KEY_ALIAS = "biometricKeyAlias"
  }

  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, Unit> {
    val dek = masterKeyProvider.getMasterKey() ?: return Either.Left(
      value = AppError.DefaultError(NoSuchElementException("Master key is not available"))
    )

    val kekBiometric = secretKeyProvider.getKeyStoreSecretKey(
      cryptography = Cryptography.AES_CBC_PKCS7,
      authenticationRequired = true,
      keyAlias = BIOMETRIC_KEY_ALIAS,
    )

    val initCipher = cryptoManager.getBaseEncryptCipher(
      cryptography = Cryptography.AES_CBC_PKCS7,
      key = kekBiometric,
    )

    biometricManager.showBiometricPrompt(
      title = "Ustawienie logowania biometrycznego",
      description = "Przytrzymaj palec w celu dodania logoawania biometrycznego do aplikacji",
      negativeButton = "Anuluj",
      cipher = initCipher,
    ).let { result ->
      when (result) {
        is BiometricResult.AuthenticationSuccess -> {

          val encryptedBiometricDek = cryptoManager.encryptWithKey(
            data = dek.encoded,
            key = kekBiometric,
            cryptography = Cryptography.AES_CBC_PKCS7,
            initialCipher = result.cipher,
          ) ?: return Either.Left(AppError.DefaultError(Exception("Encryption failed")))

          val userSettings = userRepository.getUserSettings() ?: return Either.Left(
            value = AppError.DefaultError(NoSuchElementException("User settings are not available"))
          )

          userRepository.saveNewUserSettings(
            userSettings = userSettings.copy(
              ivDekBiometric = base64Coder.encode(data = encryptedBiometricDek)
            )
          )

        }
        else -> {
          //TODO error handling
        }
      }
    }

    return Either.Right(Unit)
  }
}

