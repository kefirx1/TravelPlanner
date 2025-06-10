package pl.bla.dev.common.validators

sealed interface ValidationResult {
  data object Valid : ValidationResult
  data class Invalid(val message: String) : ValidationResult
}