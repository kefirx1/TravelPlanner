package pl.bla.dev.common.security.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.common.security.CryptoManager
import pl.bla.dev.common.security.CryptoManagerImpl
import pl.bla.dev.common.security.KeyStoreProvider
import pl.bla.dev.common.security.KeyStoreProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {

  @Singleton
  @Provides
  fun provideKeyStoreProvider(): KeyStoreProvider = KeyStoreProviderImpl()

  @Provides
  fun provideCryptoManager(
    keyStoreProvider: KeyStoreProvider,
  ): CryptoManager = CryptoManagerImpl(
    keyStoreProvider = keyStoreProvider,
  )
}