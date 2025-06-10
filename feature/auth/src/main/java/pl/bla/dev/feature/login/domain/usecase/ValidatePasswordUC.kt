package pl.bla.dev.feature.login.domain.usecase

import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.validators.TextValidator
import pl.bla.dev.common.validators.TextValidatorRule
import pl.bla.dev.common.validators.ValidationResult

interface ValidatePasswordUC: UseCase<ValidatePasswordUC.Params, ValidationResult> {
  data class Params(
    val password: String,
  ): UseCase.Params
}

internal class ValidatePasswordUCImpl(
  private val textValidator: TextValidator,
): ValidatePasswordUC {
  override suspend fun invoke(param: ValidatePasswordUC.Params): ValidationResult {
    return textValidator
      .addRule(rule = TextValidatorRule.Required)
      .addRule(rule = TextValidatorRule.MinLength(minLength = 6))
      .addRule(rule = TextValidatorRule.AtLeastOneDigit)
      .addRule(rule = TextValidatorRule.AtLeastOneSpecialCharacters)
      .addRule(rule = TextValidatorRule.AtLeastOneLowercaseLetter)
      .addRule(rule = TextValidatorRule.AtLeastOneUppercaseLetter)
      .validate(value = param.password)
  }

}