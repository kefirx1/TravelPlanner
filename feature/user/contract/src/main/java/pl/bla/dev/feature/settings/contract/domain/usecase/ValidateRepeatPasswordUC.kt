package pl.bla.dev.feature.settings.contract.domain.usecase

import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.validators.ValidationResult

interface ValidateRepeatPasswordUC: UseCase<ValidateRepeatPasswordUC.Params, ValidationResult> {
  data class Params(
    val password: String,
    val repeatedPassword: String,
  ): UseCase.Params
}
