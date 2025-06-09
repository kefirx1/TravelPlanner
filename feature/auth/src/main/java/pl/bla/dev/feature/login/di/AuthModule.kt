package pl.bla.dev.feature.login.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.feature.login.domain.usecae.ValidateUserPasswordUC
import pl.bla.dev.feature.login.domain.usecae.ValidateUserPasswordUCImpl
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapper
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapperImpl
import pl.bla.dev.feature.settings.contract.domain.usecase.DecryptUserDEKAndInjectCacheUC

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

  @Provides
  fun provideLoginScreenMapper(): LoginScreenMapper = LoginScreenMapperImpl()

  @Provides
  fun provideValidateUserPasswordUC(
    decryptUserDEKAndInjectCacheUC: DecryptUserDEKAndInjectCacheUC,
  ): ValidateUserPasswordUC = ValidateUserPasswordUCImpl(
    decryptUserDEKAndInjectCacheUC = decryptUserDEKAndInjectCacheUC,
  )
}