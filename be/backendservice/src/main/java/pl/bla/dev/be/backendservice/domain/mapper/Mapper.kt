package pl.bla.dev.be.backendservice.domain.mapper

import pl.bla.dev.be.backendservice.contract.domain.model.CityConfig
import pl.bla.dev.be.backendservice.contract.domain.model.CountryConfig
import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContent
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentItem
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentSection
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleConfig
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleType
import pl.bla.dev.be.backendservice.data.model.CityConfigDto
import pl.bla.dev.be.backendservice.data.model.CountryConfigDto
import pl.bla.dev.be.backendservice.data.model.NewTravelConfigDto
import pl.bla.dev.be.backendservice.data.model.OnboardingContentDto
import pl.bla.dev.be.backendservice.data.model.OnboardingContentItemDto
import pl.bla.dev.be.backendservice.data.model.OnboardingContentSectionDto
import pl.bla.dev.be.backendservice.data.model.VehicleConfigDto
import pl.bla.dev.be.backendservice.data.model.VehicleTypeDto

object Mapper {
  fun OnboardingContentDto.toDomain() = OnboardingContent(
    content = content.map { it.toDomain() }
  )

  fun OnboardingContentSectionDto.toDomain() = OnboardingContentSection(
    sectionId = sectionId,
    title = title,
    content = content.map { it.toDomain() }
  )

  fun OnboardingContentItemDto.toDomain() = OnboardingContentItem(
    label = label,
    valueId = valueId,
  )

  fun NewTravelConfigDto.toDomain() = NewTravelConfig(
    creatingNewTravelEnabled = creatingNewTravelEnabled,
    countriesConfig = countriesConfig.map { it.toDomain() }
  )

  fun CountryConfigDto.toDomain() = CountryConfig(
    countryId = countryId,
    continentId = continentId,
    countryName = countryName,
    citiesConfig = citiesConfig.map { it.toDomain() }
  )

  fun CityConfigDto.toDomain() = CityConfig(
    cityId = cityId,
    cityName = cityName,
    vehiclesConfig = vehiclesConfig.map { it.toDomain() }
  )

  fun VehicleConfigDto.toDomain() = VehicleConfig(
    vehicleId = vehicleId,
    vehicleName = vehicleName,
    vehicleDescription = vehicleDescription,
    vehicleType = vehicleType.toDomain(),
    vehicleAddress = vehicleAddress,
    vehicleLatitude = vehicleLatitude,
    vehicleLongitude = vehicleLongitude,
  )

  fun VehicleTypeDto.toDomain() = when (this) {
    VehicleTypeDto.CAR -> VehicleType.CAR
    VehicleTypeDto.TRAIN -> VehicleType.TRAIN
    VehicleTypeDto.PLANE -> VehicleType.PLANE
    VehicleTypeDto.BUS -> VehicleType.BUS
  }
}