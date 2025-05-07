package pl.bla.dev.common.usecase

interface UseCase<PARAM : UseCase.Params, RESULT> {
  interface Params {
    data object Empty: Params
  }
  suspend operator fun invoke(param: PARAM): RESULT
}

interface EitherUseCase<PARAM : UseCase.Params, RESULT> {
  suspend operator fun invoke(param: PARAM): Either<Exception, RESULT>
}