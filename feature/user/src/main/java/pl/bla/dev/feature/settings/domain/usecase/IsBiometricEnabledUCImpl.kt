package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.feature.settings.contract.domain.usecase.IsBiometricEnabledUC
import pl.bla.dev.feature.settings.data.repository.UserRepository

class IsBiometricEnabledUCImpl(
  private val userRepository: UserRepository,
) : IsBiometricEnabledUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, Boolean> {
    val userSettings = userRepository.getUserSettings() ?: return Either.Left(
      value = AppError.DefaultError(NoSuchElementException("User settings are not available"))
    )

    return Either.Right(value = userSettings.ivDekBiometric != null)
  }
}