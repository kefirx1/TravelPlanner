package pl.bla.dev.common.validators

interface Validator {
  fun addRule(rule: ValidatorRule): Validator
  fun validate(value: String): ValidationResult
}

interface TextValidator : Validator

internal class TextValidatorImpl: TextValidator {
  private val rules = mutableListOf<ValidatorRule>()

  override fun addRule(rule: ValidatorRule): Validator {
    rules.add(rule)
    return this
  }

  override fun validate(value: String): ValidationResult {
    val firstInvalid = rules.find { rule ->
      rule.check(value = value).not()
    }

    rules.clear()
    return when (firstInvalid) {
      null -> ValidationResult.Valid
      else -> ValidationResult.Invalid(message = firstInvalid.defaultMessage)
    }
  }
}
