package pl.bla.dev.common.sensor.gps

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface GPSManager {
  suspend fun getCurrentLocation(): Either<AppError, Location>
}

internal class GPSManagerImpl(
  private val context: Context,
  private val locationProvider: FusedLocationProviderClient,
) : GPSManager {

  @OptIn(ExperimentalCoroutinesApi::class)
  @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
  override suspend fun getCurrentLocation(): Either<AppError, Location> = suspendCoroutine { continuation ->
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    if (!isGPSEnabled || !isNetworkEnabled) {
      continuation.resume(Either.Left(value = AppError.DefaultError(Exception("GPS is not enabled"))))
    }


    locationProvider.lastLocation
      .addOnSuccessListener { location ->
        continuation.resume(Either.Right(value = location))
      }
      .addOnFailureListener { error ->
        continuation.resume(Either.Left(value = AppError.DefaultError(error)))
      }
  }
}