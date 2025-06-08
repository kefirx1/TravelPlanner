package pl.bla.dev.feature.login.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.feature.login.domain.usecae.GetSavedUserNameUC
import pl.bla.dev.feature.login.domain.usecae.GetSavedUserNameUCImpl
import pl.bla.dev.feature.login.domain.usecae.ValidateUserPasswordUC
import pl.bla.dev.feature.login.domain.usecae.ValidateUserPasswordUCImpl
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapper
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapperImpl

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

  @Provides
  fun provideLoginScreenMapper(): LoginScreenMapper = LoginScreenMapperImpl()

  @Provides
  fun provideIsAppRegisteredUC(): GetSavedUserNameUC = GetSavedUserNameUCImpl()

  @Provides
  fun provideValidateUserPasswordUC(): ValidateUserPasswordUC = ValidateUserPasswordUCImpl()
}