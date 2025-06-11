package pl.bla.dev.feature.settings.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewTravelConfigDto(
  @SerialName("creatingNewTravelEnabled")val creatingNewTravelEnabled: Boolean,
  @SerialName("countriesConfig")val countriesConfig: List<CountryConfigDto>,
)

@Serializable
data class CountryConfigDto(
  @SerialName("countryId")val countryId: Int,
  @SerialName("countryName")val countryName: String,
  @SerialName("continentId")val continentId: Int,
  @SerialName("citiesConfig")val citiesConfig: List<CityConfigDto>,
)

@Serializable
data class CityConfigDto(
  @SerialName("cityId")val cityId: Int,
  @SerialName("cityName")val cityName: String,
  @SerialName("vehiclesConfig")val vehiclesConfig: List<VehicleConfigDto>,
)

@Serializable
data class VehicleConfigDto(
  @SerialName("vehicleId")val vehicleId: Int,
  @SerialName("vehicleName")val vehicleName: String,
  @SerialName("vehicleDescription")val vehicleDescription: String,
  @SerialName("vehicleType")val vehicleType: VehicleTypeDto,
  @SerialName("vehicleAddress")val vehicleAddress: String,
  @SerialName("vehicleLatitude")val vehicleLatitude: Double,
  @SerialName("vehicleLongitude")val vehicleLongitude: Double,
)

@Serializable
enum class VehicleTypeDto {
  @SerialName("CAR")CAR,
  @SerialName("TRAIN")TRAIN,
  @SerialName("PLANE")PLANE,
  @SerialName("BUS")BUS,
}