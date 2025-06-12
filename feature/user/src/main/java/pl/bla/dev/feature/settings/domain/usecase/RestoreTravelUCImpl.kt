package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.feature.settings.contract.domain.usecase.RestoreTravelUC
import pl.bla.dev.feature.settings.data.repository.UserRepository

class RestoreTravelUCImpl(
  private val userRepository: UserRepository,
) : RestoreTravelUC {
  override suspend fun invoke(param: RestoreTravelUC.Params): Either<AppError, Unit> {
    userRepository.updateUserTravelCancellationStatus(
      travelId = param.travelId,
      isCancelled = false,
    )
    return Either.Right(Unit)
  }

}