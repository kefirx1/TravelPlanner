package pl.bla.dev.common.biometric.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.common.biometric.BiometricPromptConnector
import pl.bla.dev.common.biometric.BiometricPromptManager
import pl.bla.dev.common.biometric.BiometricPromptManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BiometricModule {

  @Singleton
  @Provides
  fun provideBiometricPromptManager() = BiometricPromptManagerImpl()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BiometricBinder {

  @Binds
  abstract fun bindBiometricPromptManager(
    biometricPromptManagerImpl: BiometricPromptManagerImpl,
  ): BiometricPromptManager

  @Binds
  abstract fun bindBiometricPromptConnector(
    biometricPromptManagerImpl: BiometricPromptManagerImpl,
  ): BiometricPromptConnector

}