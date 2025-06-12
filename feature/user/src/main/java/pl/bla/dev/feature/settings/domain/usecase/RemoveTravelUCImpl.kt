package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.feature.settings.contract.domain.usecase.RemoveTravelUC
import pl.bla.dev.feature.settings.data.repository.UserRepository

class RemoveTravelUCImpl(
  private val userRepository: UserRepository,
) : RemoveTravelUC {
  override suspend fun invoke(param: RemoveTravelUC.Params): Either<AppError, Unit> {
    userRepository.removeUserTravel(travelId = param.travelId)
    return Either.Right(Unit)
  }

}