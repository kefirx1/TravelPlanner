package pl.bla.dev.common.sensor.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.common.sensor.gps.GPSManager
import pl.bla.dev.common.sensor.gps.GPSManagerImpl
import pl.bla.dev.common.sensor.usecase.GetCurrentLocationUC
import pl.bla.dev.common.sensor.usecase.GetCurrentLocationUCImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {

  @Singleton
  @Provides
  fun provideGPSManager(
    context: Context,
  ): GPSManager = GPSManagerImpl(
    context = context,
    locationProvider = LocationServices.getFusedLocationProviderClient(context),
  )

  @Provides
  fun provideGetCurrentLocationUC(
    gpsManager: GPSManager,
  ): GetCurrentLocationUC = GetCurrentLocationUCImpl(
    gpsManager = gpsManager,
  )
}