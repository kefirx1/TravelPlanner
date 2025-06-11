package pl.bla.dev.be.backendservice.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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