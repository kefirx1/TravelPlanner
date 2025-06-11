package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.be.backendservice.contract.domain.usecase.GetServiceNewTravelConfigUC
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.feature.settings.contract.domain.usecase.FetchNewTravelConfigUC
import pl.bla.dev.feature.settings.data.repository.UserRepository

class FetchNewTravelConfigUCImpl(
  private val getServiceNewTravelConfigUC: GetServiceNewTravelConfigUC,
  private val userRepository: UserRepository,
) : FetchNewTravelConfigUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, Unit> {
    return getServiceNewTravelConfigUC(UseCase.Params.Empty).fold(
      onRight = { config ->
        userRepository.saveNewTravelConfig(newTravelConfig = config)

        Either.Right(Unit)
      },
      onLeft = { error ->
        //error handling
        Either.Left(error)
      }
    )
  }
}