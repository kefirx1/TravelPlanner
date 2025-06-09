package pl.bla.dev.be.backendservice.contract.domain.usecase

import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContent
import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase

interface GetOnboardingContentUC : EitherUseCase<UseCase.Params.Empty, OnboardingContent>