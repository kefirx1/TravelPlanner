package pl.bla.dev.feature.login.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.feature.settings.contract.domain.usecase.DecryptUserDEKAndInjectCacheUC

interface ValidateUserPasswordUC : EitherUseCase<ValidateUserPasswordUC.Params, ValidateUserPasswordUC.Result> {
  data class Params(
    val typedPassword: String,
  ): UseCase.Params

  sealed interface Result {
    data object Success : Result
    data object WrongPassword : Result
  }
}


class ValidateUserPasswordUCImpl(
  private val decryptUserDEKAndInjectCacheUC: DecryptUserDEKAndInjectCacheUC,
) : ValidateUserPasswordUC {
  override suspend fun invoke(param: ValidateUserPasswordUC.Params): Either<AppError, ValidateUserPasswordUC.Result> {
    return decryptUserDEKAndInjectCacheUC(
      param = DecryptUserDEKAndInjectCacheUC.Params(
        password = param.typedPassword
      )
    ).fold(
      onRight = {
        Either.Right(value = ValidateUserPasswordUC.Result.Success)
      },
      onLeft = { error ->
        Either.Right(value = ValidateUserPasswordUC.Result.WrongPassword)
      },
    )
  }
}