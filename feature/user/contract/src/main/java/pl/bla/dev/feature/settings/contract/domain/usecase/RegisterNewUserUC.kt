package pl.bla.dev.feature.settings.contract.domain.usecase

import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentSection
import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase

interface RegisterNewUserUC : EitherUseCase<RegisterNewUserUC.Params, Unit> {
  data class Params(
    val userPassword: String,
    val userName: String,
    val userEmail: String,
    val selectedChips: List<OnboardingContentSection>,
  ): UseCase.Params
}