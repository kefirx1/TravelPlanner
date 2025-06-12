package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.be.backendservice.contract.domain.model.CityConfig
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleConfig
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.feature.settings.contract.domain.model.UserTravels
import pl.bla.dev.feature.settings.contract.domain.usecase.SaveNewTravelUC
import pl.bla.dev.feature.settings.data.repository.UserRepository

class SaveNewTravelUCImpl(
  private val userRepository: UserRepository,
  private val getCountryTravelConfigByIdUC: GetCountryTravelConfigByIdUC,
) : SaveNewTravelUC {
  override suspend fun invoke(param: SaveNewTravelUC.Params): Either<AppError, Int> {
    return getCountryTravelConfigByIdUC(
      param = GetCountryTravelConfigByIdUC.Params(countryId = param.originCountryId),
    ).fold(
      onRight = { originConfig ->
        getCountryTravelConfigByIdUC(
          param = GetCountryTravelConfigByIdUC.Params(countryId = param.destinationCountryId),
        ).fold(
          onRight = { destinationConfig ->
            val originCityConfig = originConfig.citiesConfig.getCityConfigById(
              cityId = param.originCityId,
            ) ?: return@fold Either.Left(
              value = AppError.DefaultError(NoSuchElementException("Origin city ${param.originCityId} not found"))
            )
            val destinationCityConfig = destinationConfig.citiesConfig.getCityConfigById(
              cityId = param.destinationCityId,
            ) ?: return@fold Either.Left(
              value = AppError.DefaultError(NoSuchElementException("Destination city ${param.destinationCityId} not found"))
            )

            val originVehicleConfig = originCityConfig.vehiclesConfig.getVehicleConfigById(
              vehicleId = param.originVehicleId,
            ) ?: return@fold Either.Left(
              value = AppError.DefaultError(NoSuchElementException("Origin vehicle ${param.originVehicleId} not found"))
            )
            val destinationVehicleConfig = destinationCityConfig.vehiclesConfig.getVehicleConfigById(
              vehicleId = param.destinationVehicleId,
            ) ?: return@fold Either.Left(
              value = AppError.DefaultError(NoSuchElementException("Destination vehicle ${param.destinationVehicleId} not found"))
            )

            val travelId = userRepository.saveUserTravel(
              userTravels = UserTravels(
                originContinentId = originConfig.continentId,
                destinationContinentId = destinationConfig.continentId,
                originCityId = param.originCityId,
                originCity = originCityConfig.cityName,
                originCountryId = param.originCountryId,
                originCountry = originConfig.countryName,
                destinationCityId = param.destinationCityId,
                destinationCity = destinationCityConfig.cityName,
                destinationCountryId = param.destinationCountryId,
                destinationCountry = destinationConfig.countryName,
                startDate = param.startDate,
                endDate = param.endDate,
                originVehicleId = param.originVehicleId,
                originVehicleName = originVehicleConfig.vehicleName,
                originVehicleDescription = originVehicleConfig.vehicleDescription,
                originVehicleAddress = originVehicleConfig.vehicleAddress,
                originVehicleLatitude = originVehicleConfig.vehicleLatitude,
                originVehicleLongitude = originVehicleConfig.vehicleLongitude,
                originVehicleType = originVehicleConfig.vehicleType,
                destinationVehicleId = param.destinationVehicleId,
                destinationVehicleName = destinationVehicleConfig.vehicleName,
                destinationVehicleDescription = destinationVehicleConfig.vehicleDescription,
                destinationVehicleAddress = destinationVehicleConfig.vehicleAddress,
                destinationVehicleLatitude = destinationVehicleConfig.vehicleLatitude,
                destinationVehicleLongitude = destinationVehicleConfig.vehicleLongitude,
                destinationVehicleType = destinationVehicleConfig.vehicleType,
                cancelled = false,
              )
            )

            Either.Right(value = travelId)
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
}