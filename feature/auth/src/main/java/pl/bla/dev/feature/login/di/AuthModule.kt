package pl.bla.dev.feature.login.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.common.validators.TextValidator
import pl.bla.dev.feature.login.domain.usecase.ValidateEmailUC
import pl.bla.dev.feature.login.domain.usecase.ValidateEmailUCImpl
import pl.bla.dev.feature.login.domain.usecase.ValidatePasswordUC
import pl.bla.dev.feature.login.domain.usecase.ValidatePasswordUCImpl
import pl.bla.dev.feature.login.domain.usecase.ValidateRepeatPasswordUC
import pl.bla.dev.feature.login.domain.usecase.ValidateRepeatPasswordUCImpl
import pl.bla.dev.feature.login.domain.usecase.ValidateUserContainerPasswordUC
import pl.bla.dev.feature.login.domain.usecase.ValidateUserContainerPasswordUCImpl
import pl.bla.dev.feature.login.domain.usecase.ValidateUserNameUC
import pl.bla.dev.feature.login.domain.usecase.ValidateUserNameUCImpl
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
  fun provideValidateUserContainerPasswordUC(
    decryptUserDEKAndInjectCacheUC: DecryptUserDEKAndInjectCacheUC,
  ): ValidateUserContainerPasswordUC = ValidateUserContainerPasswordUCImpl(
    decryptUserDEKAndInjectCacheUC = decryptUserDEKAndInjectCacheUC,
  )

  @Provides
  fun provideLoginScreenDialogMapper(): LoginScreenDialogMapper = LoginScreenDialogMapperImpl()

  @Provides
  fun provideValidateEmailUC(
    textValidator: TextValidator,
  ): ValidateEmailUC = ValidateEmailUCImpl(textValidator = textValidator)

  @Provides
  fun provideValidatePasswordUC(
    textValidator: TextValidator,
  ): ValidatePasswordUC = ValidatePasswordUCImpl(textValidator = textValidator)

  @Provides
  fun provideValidateRepeatPasswordUC(
    textValidator: TextValidator,
  ): ValidateRepeatPasswordUC = ValidateRepeatPasswordUCImpl(textValidator = textValidator)


  @Provides
  fun provideValidateUserNameUC(
    textValidator: TextValidator,
  ): ValidateUserNameUC = ValidateUserNameUCImpl(textValidator = textValidator)
}