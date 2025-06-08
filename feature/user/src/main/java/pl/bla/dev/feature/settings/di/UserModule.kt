package pl.bla.dev.feature.settings.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.common.storage.datastore.DataStoreProvider
import pl.bla.dev.feature.settings.data.source.UserSettingsDataStore
import pl.bla.dev.feature.settings.data.source.UserSettingsPreferencesDataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

  @Singleton
  @Provides
  fun provideSettingsDataStore(
    dataStoreProvider: DataStoreProvider,
  ): UserSettingsDataStore = UserSettingsPreferencesDataStore(
    dataStoreProvider = dataStoreProvider,
  )
}