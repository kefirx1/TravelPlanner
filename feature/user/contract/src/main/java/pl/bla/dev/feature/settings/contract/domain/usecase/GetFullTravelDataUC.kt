package pl.bla.dev.feature.settings.contract.domain.usecase

import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.feature.settings.contract.domain.model.TravelFullData

interface GetFullTravelDataUC : EitherUseCase<GetFullTravelDataUC.Params, TravelFullData> {
  data class Params(
    val travelId: Int,
  ): UseCase.Params
}