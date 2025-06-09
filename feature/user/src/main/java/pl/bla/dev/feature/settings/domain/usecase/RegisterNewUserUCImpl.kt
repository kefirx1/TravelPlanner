package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.security.CryptoManager
import pl.bla.dev.common.security.Cryptography
import pl.bla.dev.common.security.MasterKeyProvider
import pl.bla.dev.common.security.SecretKeyProvider
import pl.bla.dev.common.security.domain.GenerateSaltUC
import pl.bla.dev.feature.settings.contract.domain.model.UserSettings
import pl.bla.dev.feature.settings.contract.domain.usecase.RegisterNewUserUC
import pl.bla.dev.feature.settings.data.model.UserInfo
import pl.bla.dev.feature.settings.data.repository.UserRepository

internal class RegisterNewUserUCImpl(
  private val userRepository: UserRepository,
  private val secretKeyProvider: SecretKeyProvider,
  private val masterKeyProvider: MasterKeyProvider,
  private val generateSaltUC: GenerateSaltUC,
  private val cryptoManager: CryptoManager,
): RegisterNewUserUC {
  override suspend fun invoke(param: RegisterNewUserUC.Params): Either<AppError, Unit> {
    val newSalt: ByteArray = generateSaltUC(UseCase.Params.Empty)
    val dek = masterKeyProvider.generateMasterKey()

    val kek = secretKeyProvider.getSecretKeyFromBase(
      cryptography = Cryptography.AES_GCM_NoPadding,
      base = param.userPassword.toCharArray(),
      salt = newSalt,
    )
    val ivWithEncryptedDEK = cryptoManager.encryptWithKey(
      data = dek.encoded,
      key = kek,
    ) ?: return Either.Left(AppError.DefaultError(Exception("Encryption failed")))

    userRepository.saveNewUserSettings(
      userSettings = UserSettings(
        userName = param.firstName,
        salt = newSalt,
        ivDek = ivWithEncryptedDEK,
      ),
    )
    userRepository.registerNewUser(
      userInfo = UserInfo(
        firstName = param.firstName,
        sureName = param.sureName,
      ),
    )

    return Either.Right(Unit)
  }
}