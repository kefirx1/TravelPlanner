package pl.bla.dev.be.backendservice.contract.domain.usecase

import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase

interface GetServiceNewTravelConfigUC : EitherUseCase<UseCase.Params.Empty, NewTravelConfig>