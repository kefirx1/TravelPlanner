package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.feature.settings.contract.domain.usecase.CancelTravelUC
import pl.bla.dev.feature.settings.data.repository.UserRepository

class CancelTravelUCImpl(
  private val userRepository: UserRepository,
) : CancelTravelUC {
  override suspend fun invoke(param: CancelTravelUC.Params): Either<AppError, Unit> {
    userRepository.updateUserTravelCancellationStatus(
      travelId = param.travelId,
      isCancelled = true,
    )
    return Either.Right(Unit)
  }

}