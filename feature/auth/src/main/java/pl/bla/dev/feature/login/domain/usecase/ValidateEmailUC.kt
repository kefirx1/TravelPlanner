package pl.bla.dev.feature.login.domain.usecase

import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.validators.TextValidator
import pl.bla.dev.common.validators.TextValidatorRule
import pl.bla.dev.common.validators.ValidationResult

interface ValidateEmailUC: UseCase<ValidateEmailUC.Params, ValidationResult> {
  data class Params(
    val email: String,
  ): UseCase.Params
}

internal class ValidateEmailUCImpl(
  private val textValidator: TextValidator,
): ValidateEmailUC {
  override suspend fun invoke(param: ValidateEmailUC.Params): ValidationResult {
    return textValidator
      .addRule(rule = TextValidatorRule.Required)
      .addRule(rule = TextValidatorRule.EmailPatter)
      .validate(value = param.email)
  }

}