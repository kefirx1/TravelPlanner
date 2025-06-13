package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.feature.settings.contract.domain.model.TravelShortData
import pl.bla.dev.feature.settings.contract.domain.model.TravelStatus
import pl.bla.dev.feature.settings.contract.domain.usecase.GetUserTravelsShortDataUC
import pl.bla.dev.feature.settings.data.repository.UserRepository
import java.time.LocalDateTime
import java.time.ZoneId

class GetUserTravelsShortDataUCImpl(
  private val userRepository: UserRepository,
) : GetUserTravelsShortDataUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, List<TravelShortData>> {
    val userTravel = userRepository.getUserTravels()

    return Either.Right(
      value = userTravel.map { travel ->
        TravelShortData(
          travelId = travel.uid,
          originCity = travel.originCity,
          originCountry = travel.originCountry,
          destinationCity = travel.destinationCity,
          destinationCountry = travel.destinationCountry,
          startDate = travel.startDate,
          endDate = travel.endDate,
          travelStatus = getTravelStatus(
            startDate = travel.startDate,
            endDate = travel.endDate,
            isCancelled = travel.cancelled,
          ),
          originVehicleType = travel.originVehicleType,
          destinationVehicleType = travel.destinationVehicleType,
          longitudeOrigin = travel.originVehicleLongitude,
          latitudeOrigin = travel.originVehicleLatitude,
          longitudeDestination = travel.destinationVehicleLongitude,
          latitudeDestination = travel.destinationVehicleLatitude,
        )
      }
    )
  }

  private fun getTravelStatus(startDate: LocalDateTime, endDate: LocalDateTime, isCancelled: Boolean): TravelStatus {
    val now = LocalDateTime.now(ZoneId.systemDefault())
    return when {
      isCancelled -> TravelStatus.CANCELLED
      now.isBefore(startDate) -> TravelStatus.FUTURE
      now.isAfter(startDate) && now.isBefore(endDate) -> TravelStatus.CURRENT
      now.isAfter(endDate) -> TravelStatus.PAST
      else -> TravelStatus.PAST
    }
  }
}