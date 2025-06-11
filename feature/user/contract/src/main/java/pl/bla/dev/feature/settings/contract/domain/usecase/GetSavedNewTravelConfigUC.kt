package pl.bla.dev.feature.settings.contract.domain.usecase

import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase

interface GetSavedNewTravelConfigUC : EitherUseCase<UseCase.Params.Empty, NewTravelConfig>