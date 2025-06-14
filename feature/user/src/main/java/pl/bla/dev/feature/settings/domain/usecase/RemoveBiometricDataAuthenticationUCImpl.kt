package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.feature.settings.contract.domain.usecase.RemoveBiometricDataAuthenticationUC
import pl.bla.dev.feature.settings.data.repository.UserRepository

class RemoveBiometricDataAuthenticationUCImpl(
  private val userRepository: UserRepository,
) : RemoveBiometricDataAuthenticationUC {

  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, Unit> {
    val userSettings = userRepository.getUserSettings() ?: return Either.Left(
      value = AppError.DefaultError(NoSuchElementException("User settings are not available"))
    )

    userRepository.saveNewUserSettings(
      userSettings = userSettings.copy(
        ivDekBiometric = null,
      )
    )

    return Either.Right(Unit)
  }
}

