package pl.bla.dev.feature.settings.domain.mapper

import pl.bla.dev.be.backendservice.contract.domain.model.CityConfig
import pl.bla.dev.be.backendservice.contract.domain.model.CountryConfig
import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentItem
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentSection
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleConfig
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleType
import pl.bla.dev.feature.settings.contract.domain.model.TravelStatus
import pl.bla.dev.feature.settings.contract.domain.model.UserInfo
import pl.bla.dev.feature.settings.contract.domain.model.UserOnboardingPreferences
import pl.bla.dev.feature.settings.contract.domain.model.UserOnboardingPreferencesItem
import pl.bla.dev.feature.settings.contract.domain.model.UserOnboardingPreferencesSection
import pl.bla.dev.feature.settings.contract.domain.model.UserSettings
import pl.bla.dev.feature.settings.contract.domain.model.UserTravels
import pl.bla.dev.feature.settings.data.model.CityConfigDto
import pl.bla.dev.feature.settings.data.model.CountryConfigDto
import pl.bla.dev.feature.settings.data.model.NewTravelConfigDto
import pl.bla.dev.feature.settings.data.model.UserInfoDto
import pl.bla.dev.feature.settings.data.model.UserOnboardingPreferencesDto
import pl.bla.dev.feature.settings.data.model.UserOnboardingPreferencesItemDto
import pl.bla.dev.feature.settings.data.model.UserOnboardingPreferencesSectionDto
import pl.bla.dev.feature.settings.data.model.UserSettingsDto
import pl.bla.dev.feature.settings.data.model.UserTravelsDto
import pl.bla.dev.feature.settings.data.model.VehicleConfigDto
import pl.bla.dev.feature.settings.data.model.VehicleTypeDto

object UserMapper {

  fun UserSettingsDto.toDomain(): UserSettings =
    UserSettings(
      userName = userName,
      salt = salt,
      ivDek = ivDek,
    )

  fun UserSettings.toDto(): UserSettingsDto =
    UserSettingsDto(
      userName = userName,
      salt = salt,
      ivDek = ivDek,
    )

  fun OnboardingContentSection.toDto() = UserOnboardingPreferencesSectionDto(
    content = content.map { it.toDto() },
    sectionId = sectionId,
    title = title,
  )

  fun OnboardingContentItem.toDto() = UserOnboardingPreferencesItemDto(
    label = label,
    valueId = valueId,
  )

  fun NewTravelConfig.toDto() = NewTravelConfigDto(
    creatingNewTravelEnabled = creatingNewTravelEnabled,
    countriesConfig = countriesConfig.map { it.toDto() },
  )

  fun NewTravelConfigDto.toDomain() = NewTravelConfig(
    creatingNewTravelEnabled = creatingNewTravelEnabled,
    countriesConfig = countriesConfig.map { it.toDomain() },
  )

  fun CountryConfig.toDto() = CountryConfigDto(
    countryId = countryId,
    countryName = countryName,
    continentId = continentId,
    citiesConfig = citiesConfig.map { it.toDto() },
  )

  fun CountryConfigDto.toDomain() = CountryConfig(
    countryId = countryId,
    countryName = countryName,
    continentId = continentId,
    citiesConfig = citiesConfig.map { it.toDomain() },
  )

  fun CityConfig.toDto() = CityConfigDto(
    cityId = cityId,
    cityName = cityName,
    vehiclesConfig = vehiclesConfig.map { it.toDto() },
  )

  fun CityConfigDto.toDomain() = CityConfig(
    cityId = cityId,
    cityName = cityName,
    vehiclesConfig = vehiclesConfig.map { it.toDomain() },
  )

  fun VehicleConfig.toDto() = VehicleConfigDto(
    vehicleId = vehicleId,
    vehicleName = vehicleName,
    vehicleDescription = vehicleDescription,
    vehicleType = vehicleType.toDto(),
    vehicleAddress = vehicleAddress,
    vehicleLatitude = vehicleLatitude,
    vehicleLongitude = vehicleLongitude,
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

  fun VehicleType.toDto() = when (this) {
    VehicleType.CAR -> VehicleTypeDto.CAR
    VehicleType.TRAIN -> VehicleTypeDto.TRAIN
    VehicleType.PLANE -> VehicleTypeDto.PLANE
    VehicleType.BUS -> VehicleTypeDto.BUS
  }

  fun VehicleTypeDto.toDomain() = when (this) {
    VehicleTypeDto.CAR -> VehicleType.CAR
    VehicleTypeDto.TRAIN -> VehicleType.TRAIN
    VehicleTypeDto.PLANE -> VehicleType.PLANE
    VehicleTypeDto.BUS -> VehicleType.BUS
  }

  fun UserInfoDto.toDomain() = UserInfo(
    uid = uid,
    firstName = firstName,
    sureName = sureName,
    email = email,
    onboardingPreferences = onboardingPreferences.toDomain(),
  )

  fun UserOnboardingPreferencesSectionDto.toDomain() = UserOnboardingPreferencesSection(
    sectionId = sectionId,
    title = title,
    content = content.map { it.toDomain() },
  )

  fun UserOnboardingPreferencesItemDto.toDomain() = UserOnboardingPreferencesItem(
    label = label,
    valueId = valueId,
  )

  fun UserOnboardingPreferencesSection.toDto() = UserOnboardingPreferencesSectionDto(
    sectionId = sectionId,
    title = title,
    content = content.map { it.toDto() },
  )

  fun UserOnboardingPreferencesItem.toDto() = UserOnboardingPreferencesItemDto(
    label = label,
    valueId = valueId,
  )


  fun UserOnboardingPreferencesDto.toDomain() = UserOnboardingPreferences(
    content = content.map { it.toDomain() },
  )

  fun UserOnboardingPreferences.toDto() = UserOnboardingPreferencesDto(
    content = content.map { it.toDto() },
  )

  fun UserTravelsDto.toDomain() = UserTravels(
    uid = uid,
    userId = userId,
    originCountryId = originCountryId,
    destinationCountryId = destinationCountryId,
    originCityId = originCityId,
    destinationCityId = destinationCityId,
    cancelled = cancelled,
    startDate = startDate,
    endDate = endDate,
    originVehicleId = originVehicleId,
    destinationVehicleId = destinationVehicleId,
  )


}