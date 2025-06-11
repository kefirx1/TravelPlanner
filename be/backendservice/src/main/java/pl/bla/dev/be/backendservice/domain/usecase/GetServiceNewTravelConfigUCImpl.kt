package pl.bla.dev.be.backendservice.domain.usecase

import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.be.backendservice.contract.domain.usecase.GetServiceNewTravelConfigUC
import pl.bla.dev.be.backendservice.data.repository.BackendServiceRepository
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase

class GetServiceNewTravelConfigUCImpl(
  private val backendServiceRepository: BackendServiceRepository,
) : GetServiceNewTravelConfigUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, NewTravelConfig> =
    backendServiceRepository.getNewTravelConfig()

}