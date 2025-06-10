package pl.bla.dev.feature.login.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.feature.login.domain.usecase.ValidateUserPasswordUC
import pl.bla.dev.feature.login.domain.usecase.ValidateUserPasswordUCImpl
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenDialogMapper
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenDialogMapperImpl
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapper
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapperImpl
import pl.bla.dev.feature.login.presentation.screen.onboarding.mapper.OnboardingScreenMapper
import pl.bla.dev.feature.login.presentation.screen.onboarding.mapper.OnboardingScreenMapperImpl
import pl.bla.dev.feature.login.presentation.screen.registration.mapper.RegistrationScreenMapper
import pl.bla.dev.feature.login.presentation.screen.registration.mapper.RegistrationScreenMapperImpl
import pl.bla.dev.feature.settings.contract.domain.usecase.DecryptUserDEKAndInjectCacheUC

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

  @Provides
  fun provideLoginScreenMapper(): LoginScreenMapper =
    LoginScreenMapperImpl()

  @Provides
  fun provideOnboardingScreenMapper(): OnboardingScreenMapper =
    OnboardingScreenMapperImpl()

  @Provides
  fun provideRegistrationScreenMapper(): RegistrationScreenMapper =
    RegistrationScreenMapperImpl()

  @Provides
  fun provideValidateUserPasswordUC(
    decryptUserDEKAndInjectCacheUC: DecryptUserDEKAndInjectCacheUC,
  ): ValidateUserPasswordUC = ValidateUserPasswordUCImpl(
    decryptUserDEKAndInjectCacheUC = decryptUserDEKAndInjectCacheUC,
  )

  @Provides
  fun provideLoginScreenDialogMapper(): LoginScreenDialogMapper = LoginScreenDialogMapperImpl()
}