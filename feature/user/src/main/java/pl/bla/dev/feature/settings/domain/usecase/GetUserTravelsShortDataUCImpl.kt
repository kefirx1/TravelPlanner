package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.be.backendservice.contract.domain.model.CityConfig
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleConfig
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.feature.settings.contract.domain.model.TravelShortData
import pl.bla.dev.feature.settings.contract.domain.model.TravelStatus
import pl.bla.dev.feature.settings.contract.domain.usecase.GetUserTravelsShortDataUC
import pl.bla.dev.feature.settings.data.repository.UserRepository
import java.time.LocalDateTime

class GetUserTravelsShortDataUCImpl(
  private val userRepository: UserRepository,
  private val getCountryTravelConfigByIdUC: GetCountryTravelConfigByIdUC,
) : GetUserTravelsShortDataUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, List<TravelShortData>> {
    val userTravel = userRepository.getUserTravels()

    return Either.Right(
      value = userTravel.mapNotNull { travel ->
        getCountryTravelConfigByIdUC(
          param = GetCountryTravelConfigByIdUC.Params(countryId = travel.originCountryId),
        ).fold(
          onRight = { originConfig ->
            getCountryTravelConfigByIdUC(
              param = GetCountryTravelConfigByIdUC.Params(countryId = travel.destinationCountryId),
            ).fold(
              onRight = { destinationConfig ->
                val originCityConfig = originConfig.citiesConfig.getCityConfigById(
                  cityId = travel.originCityId,
                ) ?: return@fold null
                val destinationCityConfig = destinationConfig.citiesConfig.getCityConfigById(
                  cityId = travel.destinationCityId,
                ) ?: return@fold null

                val originVehicleConfig = originCityConfig.vehiclesConfig.getVehicleConfigById(
                  vehicleId = travel.originVehicleId,
                ) ?: return@fold null
                val destinationVehicleConfig = destinationCityConfig.vehiclesConfig.getVehicleConfigById(
                  vehicleId = travel.destinationVehicleId,
                ) ?: return@fold null

                TravelShortData(
                  travelId = travel.uid,
                  originCity = originCityConfig.cityName,
                  originCountry = originConfig.countryName,
                  destinationCity = destinationCityConfig.cityName,
                  destinationCountry = destinationConfig.countryName,
                  startDate = travel.startDate,
                  endDate = travel.endDate,
                  travelStatus = getTravelStatus(
                    startDate = travel.startDate,
                    endDate = travel.endDate,
                    isCancelled = travel.cancelled,
                  ),
                  originVehicleType = originVehicleConfig.vehicleType,
                  destinationVehicleType = destinationVehicleConfig.vehicleType,
                )
              },
              onLeft = { return@fold null }
            )
          },
          onLeft = { return@fold null }
        )
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