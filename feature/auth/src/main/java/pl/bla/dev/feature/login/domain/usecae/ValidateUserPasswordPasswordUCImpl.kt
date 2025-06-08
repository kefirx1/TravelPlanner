package pl.bla.dev.feature.login.domain.usecae

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase

interface ValidateUserPasswordUC : EitherUseCase<ValidateUserPasswordUC.Params, ValidateUserPasswordUC.Result> {
  data class Params(
    val typedPassword: String,
  ): UseCase.Params

  sealed interface Result {
    data object Success : Result
    data object WrongPassword : Result
  }
}


class ValidateUserPasswordUCImpl : ValidateUserPasswordUC {
  override suspend fun invoke(param: ValidateUserPasswordUC.Params): Either<AppError, ValidateUserPasswordUC.Result> {
    return if (param.typedPassword == "123")
      Either.Right(value = ValidateUserPasswordUC.Result.Success)
    else
      Either.Right(value = ValidateUserPasswordUC.Result.WrongPassword)
  }
}