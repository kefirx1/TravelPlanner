package pl.bla.dev.feature.settings.contract.domain.usecase

import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase

interface RegisterNewUserUC : EitherUseCase<RegisterNewUserUC.Params, Unit> {
  data class Params(
    val userPassword: String,
    val firstName: String,
    val sureName: String,
  ): UseCase.Params
}