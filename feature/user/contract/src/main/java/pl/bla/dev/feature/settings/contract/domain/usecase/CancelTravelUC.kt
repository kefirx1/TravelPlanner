package pl.bla.dev.feature.settings.contract.domain.usecase

import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase

interface CancelTravelUC : EitherUseCase<CancelTravelUC.Params, Unit> {
  data class Params(
    val travelId: Int,
  ) : UseCase.Params
}