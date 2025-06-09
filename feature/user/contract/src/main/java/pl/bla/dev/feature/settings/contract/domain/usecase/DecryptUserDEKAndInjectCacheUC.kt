package pl.bla.dev.feature.settings.contract.domain.usecase

import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase

interface DecryptUserDEKAndInjectCacheUC : EitherUseCase<DecryptUserDEKAndInjectCacheUC.Params, Unit> {
  data class Params(
    val password: String,
  ): UseCase.Params
}