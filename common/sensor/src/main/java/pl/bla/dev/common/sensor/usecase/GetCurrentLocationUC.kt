package pl.bla.dev.common.sensor.usecase

import android.location.Location
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.sensor.gps.GPSManager

interface GetCurrentLocationUC : EitherUseCase<UseCase.Params.Empty, Location>

internal class GetCurrentLocationUCImpl(
  private val gpsManager: GPSManager,
) : GetCurrentLocationUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, Location> =
    gpsManager.getCurrentLocation()

}