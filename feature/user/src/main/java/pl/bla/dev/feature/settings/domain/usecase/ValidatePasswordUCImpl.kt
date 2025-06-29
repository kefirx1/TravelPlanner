package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.validators.TextValidator
import pl.bla.dev.common.validators.TextValidatorRule
import pl.bla.dev.common.validators.ValidationResult
import pl.bla.dev.feature.settings.contract.domain.usecase.ValidatePasswordUC


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