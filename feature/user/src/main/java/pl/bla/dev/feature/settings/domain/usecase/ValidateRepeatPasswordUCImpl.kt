package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.validators.TextValidator
import pl.bla.dev.common.validators.TextValidatorRule
import pl.bla.dev.common.validators.ValidationResult
import pl.bla.dev.feature.settings.contract.domain.usecase.ValidateRepeatPasswordUC

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