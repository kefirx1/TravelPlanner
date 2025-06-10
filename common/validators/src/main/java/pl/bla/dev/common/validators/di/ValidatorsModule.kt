package pl.bla.dev.common.validators.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.common.validators.TextValidator
import pl.bla.dev.common.validators.TextValidatorImpl

@Module
@InstallIn(SingletonComponent::class)
object ValidatorsModule {

  @Provides
  fun provideTextValidator(): TextValidator = TextValidatorImpl()
}