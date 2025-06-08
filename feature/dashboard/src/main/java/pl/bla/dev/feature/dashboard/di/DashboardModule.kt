package pl.bla.dev.feature.dashboard.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.feature.dashboard.presentation.screen.mapper.DashboardDialogMapper
import pl.bla.dev.feature.dashboard.presentation.screen.mapper.DashboardDialogMapperImpl

@Module
@InstallIn(SingletonComponent::class)
object DashboardModule {

  @Provides
  fun provideDashboardDialogMapper(): DashboardDialogMapper = DashboardDialogMapperImpl()
}