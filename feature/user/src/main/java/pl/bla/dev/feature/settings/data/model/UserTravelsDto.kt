package pl.bla.dev.feature.settings.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import pl.bla.dev.common.core.converters.JsonSerializer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
data class UserTravelsDto(
  @PrimaryKey(autoGenerate = true) val uid: Int = 0,
  @ColumnInfo(name = "user_id") val userId: Int,
  @ColumnInfo(name = "cancelled") val cancelled: Boolean,
  @ColumnInfo(name = "origin_continent_id") val originContinentId: Int,
  @ColumnInfo(name = "destination_continent_id") val destinationContinentId: Int,
  @ColumnInfo(name = "origin_city_id") val originCityId: Int,
  @ColumnInfo(name = "origin_city")val originCity: String,
  @ColumnInfo(name = "origin_country_id") val originCountryId: Int,
  @ColumnInfo(name = "origin_country") val originCountry: String,
  @ColumnInfo(name = "destination_city_id")val destinationCityId: Int,
  @ColumnInfo(name = "destination_city") val destinationCity: String,
  @ColumnInfo(name = "destination_country_id")val destinationCountryId: Int,
  @ColumnInfo(name = "destination_country") val destinationCountry: String,
  @ColumnInfo(name = "start_date") val startDate: LocalDateTime,
  @ColumnInfo(name = "end_date") val endDate: LocalDateTime,
  @ColumnInfo(name = "origin_vehicle_id") val originVehicleId: Int,
  @ColumnInfo(name = "origin_vehicle_name")val originVehicleName: String,
  @ColumnInfo(name = "origin_vehicle_description")val originVehicleDescription: String,
  @ColumnInfo(name = "origin_vehicle_address")val originVehicleAddress: String,
  @ColumnInfo(name = "origin_vehicle_latitude")val originVehicleLatitude: Double,
  @ColumnInfo(name = "origin_vehicle_longitude")val originVehicleLongitude: Double,
  @ColumnInfo(name = "origin_vehicle_type")val originVehicleType: String,
  @ColumnInfo(name = "destination_vehicle_id")val destinationVehicleId: Int,
  @ColumnInfo(name = "destination_vehicle_name")val destinationVehicleName: String,
  @ColumnInfo(name = "destination_vehicle_description")val destinationVehicleDescription: String,
  @ColumnInfo(name = "destination_vehicle_address")val destinationVehicleAddress: String,
  @ColumnInfo(name = "destination_vehicle_latitude")val destinationVehicleLatitude: Double,
  @ColumnInfo(name = "destination_vehicle_longitude")val destinationVehicleLongitude: Double,
  @ColumnInfo(name = "destination_vehicle_type")val destinationVehicleType: String,
)

@ProvidedTypeConverter
class LocalDateTimeConverter(
  private val jsonSerializer: JsonSerializer,
) {
  private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
  @TypeConverter
  fun fromLocalDateTime(date: LocalDateTime?): String? {
    if (date == null) {
      return null
    }
    return date.format(formatter)
  }

  @TypeConverter
  fun toLocalDateTime(json: String?): LocalDateTime? {
    if (json == null) {
      return null
    }
    return formatter.parse(json, LocalDateTime::from)
  }
}