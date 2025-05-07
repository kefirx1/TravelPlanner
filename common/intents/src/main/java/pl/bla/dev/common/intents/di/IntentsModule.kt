package pl.bla.dev.common.intents.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.common.intents.IntentsActivityConnector
import pl.bla.dev.common.intents.IntentsManager
import pl.bla.dev.common.intents.IntentsManagerImpl
import pl.bla.dev.common.intents.domain.usecase.OpenAppSettingsIntentUC
import pl.bla.dev.common.intents.domain.usecase.OpenAppSettingsIntentUCImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IntentsModule {

  @Provides
  fun provideOpenAppSettingsIntentUC(
    intentsManager: IntentsManager,
  ): OpenAppSettingsIntentUC = OpenAppSettingsIntentUCImpl(intentsManager = intentsManager)

  @Provides
  @Singleton
  fun provideIntentsManager() = IntentsManagerImpl()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class IntentsBinder {

  @Binds
  abstract fun bindIntentsManager(
    intentsManagerImpl: IntentsManagerImpl,
  ): IntentsManager

  @Binds
  abstract fun bindIntentsActivityConnector(
    intentsManagerImpl: IntentsManagerImpl,
  ): IntentsActivityConnector
}