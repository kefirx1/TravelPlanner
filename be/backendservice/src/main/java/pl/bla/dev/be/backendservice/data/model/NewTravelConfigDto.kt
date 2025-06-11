package pl.bla.dev.be.backendservice.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//TODO swagger generate DTO with openApi
@Serializable
data class NewTravelConfigDto(
  @SerialName("creatingNewTravelEnabled")val creatingNewTravelEnabled: Boolean,
  @SerialName("countriesConfig")val countriesConfig: List<CountryConfigDto>,
)
