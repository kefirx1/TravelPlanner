package pl.bla.dev.be.backendservice.contract.domain.model


data class NewTravelConfig(
  val creatingNewTravelEnabled: Boolean,
  val countriesConfig: List<CountryConfig>,
)

data class CountryConfig(
  val countryId: Int,
  val continentId: Int,
  val countryName: String,
  val citiesConfig: List<CityConfig>,
)

data class CityConfig(
  val cityId: Int,
  val cityName: String,
  val vehiclesConfig: List<VehicleConfig>,
)

data class VehicleConfig(
  val vehicleId: Int,
  val vehicleName: String,
  val vehicleDescription: String,
  val vehicleType: VehicleType,
  val vehicleAddress: String,
  val vehicleLatitude: Double,
  val vehicleLongitude: Double,
)

enum class VehicleType {
  CAR,
  TRAIN,
  PLANE,
  BUS,
}