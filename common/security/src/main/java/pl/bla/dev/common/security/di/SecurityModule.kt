package pl.bla.dev.common.security.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.common.security.CryptoManager
import pl.bla.dev.common.security.CryptoManagerImpl
import pl.bla.dev.common.security.MasterKeyProvider
import pl.bla.dev.common.security.MasterKeyProviderImpl
import pl.bla.dev.common.security.SecretKeyProvider
import pl.bla.dev.common.security.SecretKeyProviderImpl
import pl.bla.dev.common.security.data.MasterKeyDataStore
import pl.bla.dev.common.security.domain.GenerateSaltUC
import pl.bla.dev.common.security.domain.GenerateSaltUCImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {

  @Singleton
  @Provides
  fun provideKeyStoreProvider(): SecretKeyProvider = SecretKeyProviderImpl()

  @Provides
  fun provideCryptoManager(
    keyStoreProvider: SecretKeyProvider,
  ): CryptoManager = CryptoManagerImpl(
    secretKeyProvider = keyStoreProvider,
  )

  @Provides
  fun provideGenerateSaltUC(): GenerateSaltUC = GenerateSaltUCImpl()

  @Provides
  fun provideMasterKeyProvider(
    masterKeyDataStore: MasterKeyDataStore,
  ): MasterKeyProvider = MasterKeyProviderImpl(
    masterKeyDataStore = masterKeyDataStore,
  )
}