package pl.bla.dev.feature.login.domain.usecase

import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.validators.TextValidator
import pl.bla.dev.common.validators.TextValidatorRule
import pl.bla.dev.common.validators.ValidationResult

interface ValidateUserNameUC: UseCase<ValidateUserNameUC.Params, ValidationResult> {
  data class Params(
    val userName: String,
  ): UseCase.Params
}

internal class ValidateUserNameUCImpl(
  private val textValidator: TextValidator,
): ValidateUserNameUC {
  override suspend fun invoke(param: ValidateUserNameUC.Params): ValidationResult {
    return textValidator
      .addRule(rule = TextValidatorRule.Required)
      .validate(value = param.userName)
  }

}