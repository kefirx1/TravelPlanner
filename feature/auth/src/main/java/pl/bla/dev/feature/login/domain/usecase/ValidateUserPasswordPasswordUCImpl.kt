package pl.bla.dev.feature.login.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.feature.settings.contract.domain.usecase.DecryptUserDEKAndInjectCacheUC

interface ValidateUserContainerPasswordUC : EitherUseCase<ValidateUserContainerPasswordUC.Params, ValidateUserContainerPasswordUC.Result> {
  data class Params(
    val typedPassword: String,
  ): UseCase.Params

  sealed interface Result {
    data object Success : Result
    data object WrongPassword : Result
  }
}


class ValidateUserContainerPasswordUCImpl(
  private val decryptUserDEKAndInjectCacheUC: DecryptUserDEKAndInjectCacheUC,
) : ValidateUserContainerPasswordUC {
  override suspend fun invoke(param: ValidateUserContainerPasswordUC.Params): Either<AppError, ValidateUserContainerPasswordUC.Result> {
    return decryptUserDEKAndInjectCacheUC(
      param = DecryptUserDEKAndInjectCacheUC.Params(
        password = param.typedPassword
      )
    ).fold(
      onRight = {
        Either.Right(value = ValidateUserContainerPasswordUC.Result.Success)
      },
      onLeft = { error ->
        Either.Right(value = ValidateUserContainerPasswordUC.Result.WrongPassword)
      },
    )
  }
}