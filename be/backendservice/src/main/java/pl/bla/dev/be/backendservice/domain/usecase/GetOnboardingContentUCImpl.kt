package pl.bla.dev.be.backendservice.domain.usecase

import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContent
import pl.bla.dev.be.backendservice.contract.domain.usecase.GetOnboardingContentUC
import pl.bla.dev.be.backendservice.data.repository.BackendServiceRepository
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase

class GetOnboardingContentUCImpl(
  private val backendServiceRepository: BackendServiceRepository,
) : GetOnboardingContentUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, OnboardingContent> =
    backendServiceRepository.getOnboardingContent()

}