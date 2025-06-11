package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.feature.settings.contract.domain.usecase.SaveNewTravelUC
import pl.bla.dev.feature.settings.data.repository.UserRepository

class SaveNewTravelUCImpl(
  private val userRepository: UserRepository,
) : SaveNewTravelUC {
  override suspend fun invoke(param: SaveNewTravelUC.Params): Either<AppError, Unit> {
    userRepository.saveUserTravel(
      originCityId = param.originCityId,
      destinationCityId = param.destinationCityId,
      originVehicleId = param.originVehicleId,
      destinationVehicleId = param.destinationVehicleId,
      destinationCountryId = param.destinationCountryId,
      originCountryId = param.originCountryId,
      startDate = param.startDate,
      endDate = param.endDate,
    )

    return Either.Right(Unit)
  }
}