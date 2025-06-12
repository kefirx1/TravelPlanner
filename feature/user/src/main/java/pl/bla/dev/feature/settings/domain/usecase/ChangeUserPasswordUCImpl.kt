package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.converters.Base64Coder
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.security.CryptoManager
import pl.bla.dev.common.security.Cryptography
import pl.bla.dev.common.security.MasterKeyProvider
import pl.bla.dev.common.security.SecretKeyProvider
import pl.bla.dev.common.security.domain.GenerateSaltUC
import pl.bla.dev.feature.settings.contract.domain.usecase.ChangeUserPasswordUC
import pl.bla.dev.feature.settings.data.repository.UserRepository

class ChangeUserPasswordUCImpl(
  private val userRepository: UserRepository,
  private val generateSaltUC: GenerateSaltUC,
  private val secretKeyProvider: SecretKeyProvider,
  private val masterKeyProvider: MasterKeyProvider,
  private val cryptoManager: CryptoManager,
  private val base64Coder: Base64Coder,
) : ChangeUserPasswordUC {
  override suspend fun invoke(param: ChangeUserPasswordUC.Params): Either<AppError, Unit> {
    val newSalt: ByteArray = generateSaltUC(UseCase.Params.Empty)

    val kek = secretKeyProvider.getSecretKeyFromBase(
      cryptography = Cryptography.AES_GCM_NoPadding,
      base = param.newPassword.toCharArray(),
      salt = newSalt,
    )
    val dek = masterKeyProvider.getMasterKey()
      ?: return Either.Left(AppError.DefaultError(Exception("Encryption failed")))

    val ivWithEncryptedDEK = cryptoManager.encryptWithKey(
      data = dek.encoded,
      key = kek,
    ) ?: return Either.Left(AppError.DefaultError(Exception("Encryption failed")))

    val userSettings = userRepository.getUserSettings()
      ?: return Either.Left(AppError.DefaultError(Exception("Encryption failed")))

    userRepository.saveNewUserSettings(
      userSettings = userSettings.copy(
        salt = base64Coder.encode(data = newSalt),
        ivDek = base64Coder.encode(data = ivWithEncryptedDEK),
      ),
    )

    return Either.Right(Unit)
  }
}