package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.feature.settings.contract.domain.usecase.GetSavedNewTravelConfigUC
import pl.bla.dev.feature.settings.data.repository.UserRepository


class GetSavedNewTravelConfigUCImpl(
  private val userRepository: UserRepository
) : GetSavedNewTravelConfigUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, NewTravelConfig> =
    userRepository.getNewTravelConfig()?.let { config -> Either.Right(value = config) }
      ?: Either.Left(value = AppError.DefaultError(NoSuchElementException("New travel config not found")))
}