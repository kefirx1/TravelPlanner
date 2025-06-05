package pl.bla.dev.common.core.error

sealed class AppError(
  open val exception: Exception,
) {
  data class NetworkError(
    override val exception: Exception,
    val code: String,
  ) : AppError(exception = exception)

  data class DefaultError(
    override val exception: Exception,
  ) : AppError(exception = exception)
}