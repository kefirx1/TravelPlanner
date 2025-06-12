package pl.bla.dev.feature.dashboard.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.feature.dashboard.presentation.screen.main.mapper.MainDashboardDialogMapper
import pl.bla.dev.feature.dashboard.presentation.screen.main.mapper.MainDashboardDialogMapperImpl
import pl.bla.dev.feature.dashboard.presentation.screen.main.mapper.MainDashboardScreenMapper
import pl.bla.dev.feature.dashboard.presentation.screen.main.mapper.MainDashboardScreenMapperImpl
import pl.bla.dev.feature.dashboard.presentation.screen.settings.changepassword.mapper.ChangePasswordScreenMapper
import pl.bla.dev.feature.dashboard.presentation.screen.settings.changepassword.mapper.ChangePasswordScreenMapperImpl

@Module
@InstallIn(SingletonComponent::class)
object DashboardModule {

  @Provides
  fun provideMainDashboardScreenMapper(): MainDashboardScreenMapper = MainDashboardScreenMapperImpl()

  @Provides
  fun provideMainDashboardDialogMapper(): MainDashboardDialogMapper = MainDashboardDialogMapperImpl()

  @Provides
  fun provideChangePasswordScreenMapper(): ChangePasswordScreenMapper = ChangePasswordScreenMapperImpl()
}