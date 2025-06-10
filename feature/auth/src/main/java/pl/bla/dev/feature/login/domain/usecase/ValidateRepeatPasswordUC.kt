package pl.bla.dev.feature.login.domain.usecase

import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.validators.TextValidator
import pl.bla.dev.common.validators.TextValidatorRule
import pl.bla.dev.common.validators.ValidationResult

interface ValidateRepeatPasswordUC: UseCase<ValidateRepeatPasswordUC.Params, ValidationResult> {
  data class Params(
    val password: String,
    val repeatedPassword: String,
  ): UseCase.Params
}

internal class ValidateRepeatPasswordUCImpl(
  private val textValidator: TextValidator,
): ValidateRepeatPasswordUC {
  override suspend fun invoke(param: ValidateRepeatPasswordUC.Params): ValidationResult {
    return textValidator
      .addRule(rule = TextValidatorRule.Required)
      .addRule(rule = TextValidatorRule.EqualPasswords(otherPassword = param.password))
      .validate(value = param.repeatedPassword)
  }

}