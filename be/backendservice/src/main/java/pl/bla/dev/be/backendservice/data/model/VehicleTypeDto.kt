package pl.bla.dev.be.backendservice.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class VehicleTypeDto {
  @SerialName("CAR")CAR,
  @SerialName("TRAIN")TRAIN,
  @SerialName("PLANE")PLANE,
  @SerialName("BUS")BUS,
}