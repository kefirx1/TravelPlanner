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
import pl.bla.dev.feature.settings.contract.domain.model.UserInfo
import pl.bla.dev.feature.settings.contract.domain.model.UserOnboardingPreferences
import pl.bla.dev.feature.settings.contract.domain.model.UserSettings
import pl.bla.dev.feature.settings.contract.domain.usecase.RegisterNewUserUC
import pl.bla.dev.feature.settings.data.model.UserOnboardingPreferencesDto
import pl.bla.dev.feature.settings.data.repository.UserRepository
import pl.bla.dev.feature.settings.domain.mapper.UserMapper.toDomain
import pl.bla.dev.feature.settings.domain.mapper.UserMapper.toDto

internal class RegisterNewUserUCImpl(
  private val userRepository: UserRepository,
  private val secretKeyProvider: SecretKeyProvider,
  private val masterKeyProvider: MasterKeyProvider,
  private val generateSaltUC: GenerateSaltUC,
  private val cryptoManager: CryptoManager,
  private val base64Coder: Base64Coder,
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
        userName = param.userName,
        salt = base64Coder.encode(data = newSalt),
        ivDek = base64Coder.encode(data = ivWithEncryptedDEK),
      ),
    )
    userRepository.registerNewUser(
      firstName = param.userName,
      email = param.userEmail,
      onboardingPreferences = UserOnboardingPreferences(
        content = param.selectedChips.map { it.toDto().toDomain() },
      ),
    )

    return Either.Right(Unit)
  }
}