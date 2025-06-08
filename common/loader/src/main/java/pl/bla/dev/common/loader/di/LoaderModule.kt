package pl.bla.dev.common.loader.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.common.loader.LoaderManager
import pl.bla.dev.common.loader.domain.RunWithLoaderUC
import pl.bla.dev.common.loader.domain.RunWithLoaderUCImpl

@Module
@InstallIn(SingletonComponent::class)
object LoaderModule {
  @Provides
  fun provideRunWithLoaderUC(
    loaderManager: LoaderManager,
  ): RunWithLoaderUC = RunWithLoaderUCImpl(
    loaderManager = loaderManager,
  )
}