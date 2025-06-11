package pl.bla.dev.be.backendservice.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryConfigDto(
  @SerialName("countryId")val countryId: Int,
  @SerialName("countryName")val countryName: String,
  @SerialName("continentId")val continentId: Int,
  @SerialName("citiesConfig")val citiesConfig: List<CityConfigDto>,
)