package pl.bla.dev.be.backendservice.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityConfigDto(
  @SerialName("cityId")val cityId: Int,
  @SerialName("cityName")val cityName: String,
  @SerialName("vehiclesConfig")val vehiclesConfig: List<VehicleConfigDto>,
)