package pl.bla.dev.feature.login.domain.usecae

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase

interface GetSavedUserNameUC : EitherUseCase<UseCase.Params.Empty, String>

class GetSavedUserNameUCImpl() : GetSavedUserNameUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, String> {
    return Either.Right(value = "Błażej") //check user registered app
  }
}