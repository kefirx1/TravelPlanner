package pl.bla.dev.common.permission.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.common.permission.PermissionManagerImpl
import pl.bla.dev.common.permission.PermissionsActivityConnector
import pl.bla.dev.common.permission.PermissionsManager
import pl.bla.dev.common.permission.domain.mapper.AppPermissionMapper
import pl.bla.dev.common.permission.domain.mapper.AppPermissionMapperImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PermissionModule {

  @Provides
  fun provideAppPermissionMapper(): AppPermissionMapper = AppPermissionMapperImpl()

  @Provides
  @Singleton
  fun providePermissionsManager(
    appPermissionMapper: AppPermissionMapper,
  ) = PermissionManagerImpl(
    appPermissionMapper = appPermissionMapper,
  )

}

@Module
@InstallIn(SingletonComponent::class)
abstract class PermissionBinder {

  @Binds
  abstract fun bindPermissionsManager(
    permissionManagerImpl: PermissionManagerImpl,
  ): PermissionsManager

  @Binds
  abstract fun bindPermissionsActivityConnector(
    permissionManagerImpl: PermissionManagerImpl,
  ): PermissionsActivityConnector
}