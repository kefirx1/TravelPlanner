package pl.bla.dev.travelplanner.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.common.activityconnector.ActivityConnector
import pl.bla.dev.common.intents.IntentsActivityConnector
import pl.bla.dev.common.permission.PermissionsActivityConnector
import pl.bla.dev.travelplanner.lifecycle.ActivityConnectorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Singleton
  @Provides
  fun provideActivityConnector(
    permissionsActivityConnector: PermissionsActivityConnector,
    intentsActivityConnector: IntentsActivityConnector,
  ): ActivityConnector = ActivityConnectorImpl(
    permissionsActivityConnector = permissionsActivityConnector,
    intentsActivityConnector = intentsActivityConnector,
  )
}