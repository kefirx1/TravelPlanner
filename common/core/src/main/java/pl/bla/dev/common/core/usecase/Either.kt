package pl.bla.dev.common.core.usecase

sealed class Either<out A, out B> {
  data class Left<out A>(val value: A): Either<A, Nothing>()
  data class Right<out B>(val value: B): Either<Nothing, B>()
}

fun Either<*, *>.onLeft(action : () -> Unit) = when (this) {
  is Either.Left<*> -> action()
  is Either.Right<*> -> {}
}

fun Either<*, *>.onRight(action : () -> Unit) = when (this) {
  is Either.Left<*> -> {}
  is Either.Right<*> -> action()
}

suspend fun <A, B> Either<A, B>.fold(
  onLeft: suspend (A) -> Unit,
  onRight: suspend (B) -> Unit,
) = when (this) {
  is Either.Left<A> -> onLeft(this.value)
  is Either.Right<B> -> onRight(this.value)
}