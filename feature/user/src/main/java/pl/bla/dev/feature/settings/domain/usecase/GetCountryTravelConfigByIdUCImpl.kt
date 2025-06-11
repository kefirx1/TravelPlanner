package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.be.backendservice.contract.domain.model.CountryConfig
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.feature.settings.contract.domain.usecase.GetSavedNewTravelConfigUC

interface GetCountryTravelConfigByIdUC : EitherUseCase<GetCountryTravelConfigByIdUC.Params, CountryConfig> {
  data class Params(
    val countryId: Int,
  ): UseCase.Params
}

class GetCountryTravelConfigByIdUCImpl(
  private val getSavedNewTravelConfigUC: GetSavedNewTravelConfigUC,
) : GetCountryTravelConfigByIdUC {
  override suspend fun invoke(param: GetCountryTravelConfigByIdUC.Params): Either<AppError, CountryConfig> =
    getSavedNewTravelConfigUC(UseCase.Params.Empty).fold(
      onRight = { newTravelConfig ->
        val countryConfig = newTravelConfig.countriesConfig.firstOrNull { it.countryId == param.countryId }
          ?: return@fold Either.Left(value = AppError.DefaultError(NullPointerException("Country ${param.countryId} config not found")))

        Either.Right(value = countryConfig)
      },
      onLeft = { error ->
        Either.Left(error)
      }
    )
}