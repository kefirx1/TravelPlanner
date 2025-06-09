package pl.bla.dev.travelplanner.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.common.activityconnector.ActivityConnector
import pl.bla.dev.common.intents.IntentsActivityConnector
import pl.bla.dev.common.loader.LoaderManager
import pl.bla.dev.common.permission.PermissionsActivityConnector
import pl.bla.dev.common.security.CryptoManager
import pl.bla.dev.common.security.data.MasterKeyDataStore
import pl.bla.dev.common.storage.datastore.DataStoreProvider
import pl.bla.dev.common.storage.room.DatabaseProvider
import pl.bla.dev.travelplanner.lifecycle.ActivityConnectorImpl
import pl.bla.dev.travelplanner.loader.LoaderManagerImpl
import pl.bla.dev.travelplanner.security.MasterKeyCacheDataStore
import pl.bla.dev.travelplanner.storage.DataStoreProviderImpl
import pl.bla.dev.travelplanner.storage.DatabaseProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  fun provideContext(@ApplicationContext context: Context): Context = context

  @Singleton
  @Provides
  fun provideGson(): Gson = Gson()

  @Singleton
  @Provides
  fun provideActivityConnector(
    permissionsActivityConnector: PermissionsActivityConnector,
    intentsActivityConnector: IntentsActivityConnector,
  ): ActivityConnector = ActivityConnectorImpl(
    permissionsActivityConnector = permissionsActivityConnector,
    intentsActivityConnector = intentsActivityConnector,
  )

  @Singleton
  @Provides
  fun provideLoaderManager(): LoaderManager = LoaderManagerImpl()

  @Singleton
  @Provides
  fun provideDataStoreProvider(
    @ApplicationContext context: Context,
    gson: Gson,
    cryptoManager: CryptoManager,
  ): DataStoreProvider = DataStoreProviderImpl(
    context = context,
    gson = gson,
    cryptoManager = cryptoManager,
  )

  @Provides
  fun provideDatabaseProvider(
    context: Context,
  ): DatabaseProvider = DatabaseProviderImpl(
    context = context,
  )

  @Singleton
  @Provides
  fun provideMasterKeyCacheDataStore(): MasterKeyDataStore = MasterKeyCacheDataStore()
}