package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.feature.settings.contract.domain.usecase.GetSavedUserNameUC
import pl.bla.dev.feature.settings.data.repository.UserRepository

class GetSavedUserNameUCImpl(
  private val userRepository: UserRepository,
) : GetSavedUserNameUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, String> {
    val userSettings = userRepository.getUserSettings()
      ?: return Either.Left(AppError.DefaultError(NullPointerException("UserSettings cannot be null")))

    return Either.Right(value = userSettings.userName)
  }
}