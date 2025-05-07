package pl.bla.dev.common.usecase

sealed interface Either<A,B> {
  data class Left<A,B>(val value: A): Either<A, Nothing>
  data class Right<A,B>(val value: B): Either<Nothing, B>
}