package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.security.CryptoManager
import pl.bla.dev.common.security.Cryptography
import pl.bla.dev.common.security.MasterKeyProvider
import pl.bla.dev.common.security.SecretKeyProvider
import pl.bla.dev.feature.settings.contract.domain.usecase.DecryptUserDEKAndInjectCacheUC
import pl.bla.dev.feature.settings.data.repository.UserRepository
import javax.crypto.spec.SecretKeySpec

class DecryptUserDEKAndInjectCacheUCImpl(
  private val userRepository: UserRepository,
  private val cryptoManager: CryptoManager,
  private val secretKeyProvider: SecretKeyProvider,
  private val masterKeyProvider: MasterKeyProvider,
) : DecryptUserDEKAndInjectCacheUC {
  override suspend fun invoke(param: DecryptUserDEKAndInjectCacheUC.Params): Either<AppError, Unit> {
    try {
      val userSettings = userRepository.getUserSettings()
        ?: return Either.Left(AppError.DefaultError(NullPointerException("UserSettings cannot be null")))

      val kek = secretKeyProvider.getSecretKeyFromBase(
        cryptography = Cryptography.AES_GCM_NoPadding,
        salt = userSettings.salt,
        base = param.password.toCharArray(),
      )

      val dek = cryptoManager.decryptWithKey(
        data = userSettings.ivDek,
        key = kek,
      ) ?: return Either.Left(AppError.DefaultError(Exception("Decryption failed wrong password")))

      masterKeyProvider.saveDecryptedMasterKey(
        masterKey = SecretKeySpec(dek, Cryptography.AES_GCM_NoPadding.algorithm),
      )

      return Either.Right(Unit)
    } catch (e: Exception) {
      return Either.Left(AppError.DefaultError(Exception("Decryption failed wrong password")))
    }
  }
}