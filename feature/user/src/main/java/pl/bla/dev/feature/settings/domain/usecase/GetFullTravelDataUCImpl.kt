package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.feature.settings.contract.domain.model.TravelFullData
import pl.bla.dev.feature.settings.contract.domain.model.TravelStatus
import pl.bla.dev.feature.settings.contract.domain.usecase.GetFullTravelDataUC
import pl.bla.dev.feature.settings.data.repository.UserRepository
import java.time.LocalDateTime
import java.time.ZoneId

class GetFullTravelDataUCImpl(
  private val userRepository: UserRepository,
) : GetFullTravelDataUC {
  override suspend fun invoke(param: GetFullTravelDataUC.Params): Either<AppError, TravelFullData>  {
    val userTravel = userRepository.getUserTravel(travelId = param.travelId) ?: return Either.Left(
      value = AppError.DefaultError(NoSuchElementException("Travel ${param.travelId} not found"))
    ) //TODO error handling

    val travel = TravelFullData(
      travelId = userTravel.uid,
      originContinentId = userTravel.originContinentId,
      destinationContinentId = userTravel.destinationContinentId,
      originCityId = userTravel.originCityId,
      originCity = userTravel.originCity,
      originCountryId = userTravel.originCountryId,
      originCountry = userTravel.originCountry,
      destinationCityId = userTravel.destinationCityId,
      destinationCity = userTravel.destinationCity,
      destinationCountryId = userTravel.destinationCountryId,
      destinationCountry = userTravel.destinationCountry,
      startDate = userTravel.startDate,
      endDate = userTravel.endDate,
      travelStatus = getTravelStatus(
        startDate = userTravel.startDate,
        endDate = userTravel.endDate,
        isCancelled = userTravel.cancelled,
      ),
      originVehicleId = userTravel.originVehicleId,
      originVehicleName = userTravel.originVehicleName,
      originVehicleDescription = userTravel.originVehicleDescription,
      originVehicleAddress = userTravel.originVehicleAddress,
      originVehicleLatitude = userTravel.originVehicleLatitude,
      originVehicleLongitude = userTravel.originVehicleLongitude,
      originVehicleType = userTravel.originVehicleType,
      destinationVehicleId = userTravel.destinationVehicleId,
      destinationVehicleName = userTravel.destinationVehicleName,
      destinationVehicleDescription = userTravel.destinationVehicleDescription,
      destinationVehicleAddress = userTravel.destinationVehicleAddress,
      destinationVehicleLatitude = userTravel.destinationVehicleLatitude,
      destinationVehicleLongitude = userTravel.destinationVehicleLongitude,
      destinationVehicleType = userTravel.destinationVehicleType,
    )

    return Either.Right(value = travel)
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