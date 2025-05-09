package pl.bla.dev.feature.login.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapper
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapperImpl

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
  @Provides
  fun provideLoginScreenMapper(): LoginScreenMapper = LoginScreenMapperImpl()
}