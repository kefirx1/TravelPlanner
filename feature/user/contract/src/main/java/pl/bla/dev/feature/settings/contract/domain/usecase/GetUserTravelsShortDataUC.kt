package pl.bla.dev.feature.settings.contract.domain.usecase

import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.feature.settings.contract.domain.model.TravelShortData

interface GetUserTravelsShortDataUC : EitherUseCase<UseCase.Params.Empty, List<TravelShortData>>
