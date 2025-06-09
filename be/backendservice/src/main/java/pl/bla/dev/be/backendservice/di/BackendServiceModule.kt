package pl.bla.dev.be.backendservice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.be.backendservice.contract.domain.usecase.GetOnboardingContentUC
import pl.bla.dev.be.backendservice.data.repository.BackendServiceRepository
import pl.bla.dev.be.backendservice.data.repository.BackendServiceRepositoryImpl
import pl.bla.dev.be.backendservice.domain.usecase.GetOnboardingContentUCImpl


@Module
@InstallIn(SingletonComponent::class)
object BackendServiceModule {

  @Provides
  fun provideGetOnboardingContentUC(
    backendServiceRepository: BackendServiceRepository,
  ): GetOnboardingContentUC = GetOnboardingContentUCImpl(
    backendServiceRepository = backendServiceRepository,
  )

  @Provides
  fun provideBackendServiceRepository(

  ): BackendServiceRepository = BackendServiceRepositoryImpl()
}