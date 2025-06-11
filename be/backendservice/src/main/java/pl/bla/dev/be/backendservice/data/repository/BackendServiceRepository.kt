package pl.bla.dev.be.backendservice.data.repository

import kotlinx.coroutines.delay
import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContent
import pl.bla.dev.be.backendservice.data.mock.BEMock
import pl.bla.dev.be.backendservice.domain.mapper.Mapper.toDomain
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.logger.Log
import pl.bla.dev.common.core.usecase.Either

interface BackendServiceRepository {
  suspend fun getOnboardingContent(): Either<AppError, OnboardingContent>
  suspend fun getNewTravelConfig(): Either<AppError, NewTravelConfig>
}

internal class BackendServiceRepositoryImpl: BackendServiceRepository {

  //TODO server value
  override suspend fun getOnboardingContent(): Either<AppError, OnboardingContent> {
    delay(1000)
    Log.i(tag = "BackendServiceRepository", message = "getOnboardingContent")
    return Either.Right(
      value = BEMock.onboardingContent.toDomain()
    )
  }

  override suspend fun getNewTravelConfig(): Either<AppError, NewTravelConfig> {
    delay(2000)
    Log.i(tag = "BackendServiceRepository", message = "getNewTravelConfig")
    return Either.Right(
      value = BEMock.newTravelConfig.toDomain()
    )
  }
}