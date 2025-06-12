package pl.bla.dev.feature.settings.contract.domain.usecase

import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase
import java.time.LocalDateTime

interface SaveNewTravelUC : EitherUseCase<SaveNewTravelUC.Params, Int> {
  data class Params(
    val originCountryId: Int,
    val destinationCountryId: Int,
    val originCityId: Int,
    val destinationCityId: Int,
    val originVehicleId: Int,
    val destinationVehicleId: Int,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
  ): UseCase.Params

}