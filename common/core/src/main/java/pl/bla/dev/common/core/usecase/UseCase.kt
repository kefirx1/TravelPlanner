package pl.bla.dev.common.core.usecase

import pl.bla.dev.common.core.error.AppError

interface UseCase<PARAM : UseCase.Params, RESULT> {
  interface Params {
    data object Empty: Params
  }
  suspend operator fun invoke(param: PARAM): RESULT
}

interface EitherUseCase<PARAM : UseCase.Params, RESULT> {
  suspend operator fun invoke(param: PARAM): Either<AppError, RESULT>
}