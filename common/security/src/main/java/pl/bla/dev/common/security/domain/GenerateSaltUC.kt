package pl.bla.dev.common.security.domain

import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.security.Cryptography
import java.security.SecureRandom

interface GenerateSaltUC: UseCase<UseCase.Params.Empty, ByteArray>


internal class GenerateSaltUCImpl: GenerateSaltUC {
  override suspend fun invoke(param: UseCase.Params.Empty): ByteArray {
    val salt = ByteArray(Cryptography.SALT_SIZE)
    SecureRandom().nextBytes(salt)
    return salt
  }

}