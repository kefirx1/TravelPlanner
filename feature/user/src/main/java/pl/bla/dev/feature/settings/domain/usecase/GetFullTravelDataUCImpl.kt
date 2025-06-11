package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.be.backendservice.contract.domain.model.CityConfig
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleConfig
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.feature.settings.contract.domain.model.TravelFullData
import pl.bla.dev.feature.settings.contract.domain.model.TravelStatus
import pl.bla.dev.feature.settings.contract.domain.usecase.GetFullTravelDataUC
import pl.bla.dev.feature.settings.data.repository.UserRepository
import java.time.LocalDateTime

class GetFullTravelDataUCImpl(
  private val getCountryTravelConfigByIdUC: GetCountryTravelConfigByIdUC,
  private val userRepository: UserRepository,
) : GetFullTravelDataUC {
  override suspend fun invoke(param: GetFullTravelDataUC.Params): Either<AppError, TravelFullData>  {
    val userTravel = userRepository.getUserTravel(travelId = param.travelId) ?: return Either.Left(
      value = AppError.DefaultError(NoSuchElementException("Travel ${param.travelId} not found"))
    ) //TODO error handling

    return getCountryTravelConfigByIdUC(
      param = GetCountryTravelConfigByIdUC.Params(countryId = userTravel.originCountryId),
    ).fold(
      onRight = { originConfig ->
        getCountryTravelConfigByIdUC(
          param = GetCountryTravelConfigByIdUC.Params(countryId = userTravel.destinationCountryId),
        ).fold(
          onRight = { destinationConfig ->
            val originCityConfig = originConfig.citiesConfig.getCityConfigById(
              cityId = userTravel.originCityId,
            ) ?: return@fold Either.Left(
              value = AppError.DefaultError(NoSuchElementException("Origin city ${userTravel.originCityId} not found"))
            )
            val destinationCityConfig = destinationConfig.citiesConfig.getCityConfigById(
              cityId = userTravel.destinationCityId,
            ) ?: return@fold Either.Left(
              value = AppError.DefaultError(NoSuchElementException("Destination city ${userTravel.destinationCityId} not found"))
            )

            val originVehicleConfig = originCityConfig.vehiclesConfig.getVehicleConfigById(
              vehicleId = userTravel.originVehicleId,
            ) ?: return@fold Either.Left(
              value = AppError.DefaultError(NoSuchElementException("Origin vehicle ${userTravel.originVehicleId} not found"))
            )
            val destinationVehicleConfig = destinationCityConfig.vehiclesConfig.getVehicleConfigById(
              vehicleId = userTravel.destinationVehicleId,
            ) ?: return@fold Either.Left(
              value = AppError.DefaultError(NoSuchElementException("Destination vehicle ${userTravel.destinationVehicleId} not found"))
            )


            val travel = TravelFullData(
              travelId = userTravel.uid,
              originContinentId = originConfig.continentId,
              destinationContinentId = destinationConfig.continentId,
              originCityId = userTravel.originCityId,
              originCity = originCityConfig.cityName,
              originCountryId = userTravel.originCountryId,
              originCountry = originConfig.countryName,
              destinationCityId = userTravel.destinationCityId,
              destinationCity = destinationCityConfig.cityName,
              destinationCountryId = userTravel.destinationCountryId,
              destinationCountry = destinationConfig.countryName,
              startDate = userTravel.startDate,
              endDate = userTravel.endDate,
              travelStatus = getTravelStatus(
                startDate = userTravel.startDate,
                endDate = userTravel.endDate,
                isCancelled = userTravel.cancelled,
              ),
              originVehicleId = userTravel.originVehicleId,
              originVehicleName = originVehicleConfig.vehicleName,
              originVehicleDescription = originVehicleConfig.vehicleDescription,
              originVehicleAddress = originVehicleConfig.vehicleAddress,
              originVehicleLatitude = originVehicleConfig.vehicleLatitude,
              originVehicleLongitude = originVehicleConfig.vehicleLongitude,
              originVehicleType = originVehicleConfig.vehicleType,
              destinationVehicleId = userTravel.destinationVehicleId,
              destinationVehicleName = destinationVehicleConfig.vehicleName,
              destinationVehicleDescription = destinationVehicleConfig.vehicleDescription,
              destinationVehicleAddress = destinationVehicleConfig.vehicleAddress,
              destinationVehicleLatitude = destinationVehicleConfig.vehicleLatitude,
              destinationVehicleLongitude = destinationVehicleConfig.vehicleLongitude,
              destinationVehicleType = destinationVehicleConfig.vehicleType,
            )

            Either.Right(value = travel)
          },
          onLeft = { error ->
            Either.Left(error)
          }
        )
      },
      onLeft = { error ->
        Either.Left(error)
      }
    )
  }

  private fun List<CityConfig>.getCityConfigById(cityId: Int): CityConfig? =
    this.find { config -> config.cityId == cityId }

  private fun List<VehicleConfig>.getVehicleConfigById(vehicleId: Int): VehicleConfig? =
    this.find { config -> config.vehicleId == vehicleId }

  private fun getTravelStatus(startDate: LocalDateTime, endDate: LocalDateTime, isCancelled: Boolean): TravelStatus =
    when {
      isCancelled -> TravelStatus.CANCELLED
      startDate.isBefore(LocalDateTime.now()) -> TravelStatus.FUTURE
      startDate.isAfter(LocalDateTime.now()) && endDate.isBefore(LocalDateTime.now()) -> TravelStatus.CURRENT
      endDate.isAfter(LocalDateTime.now()) -> TravelStatus.PAST
      else -> TravelStatus.PAST
    }
}