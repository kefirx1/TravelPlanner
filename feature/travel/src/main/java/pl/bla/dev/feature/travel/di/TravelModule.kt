package pl.bla.dev.feature.travel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.feature.travel.presentation.screen.details.mapper.TravelDetailsScreenMapper
import pl.bla.dev.feature.travel.presentation.screen.details.mapper.TravelDetailsScreenMapperImpl
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.mapper.NewTravelDateDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.mapper.NewTravelDateDialogMapperImpl
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.mapper.NewTravelDateScreenMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.mapper.NewTravelDateScreenMapperImpl
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.mapper.NewTravelDestinationDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.mapper.NewTravelDestinationDialogMapperImpl
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.mapper.NewTravelDestinationScreenMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.mapper.NewTravelDestinationScreenMapperImpl
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.mapper.NewTravelOriginDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.mapper.NewTravelOriginDialogMapperImpl
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.mapper.NewTravelOriginScreenMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.mapper.NewTravelOriginScreenMapperImpl
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper.NewTravelVehicleDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper.NewTravelVehicleDialogMapperImpl
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper.NewTravelVehicleScreenMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper.NewTravelVehicleScreenMapperImpl

@Module
@InstallIn(SingletonComponent::class)
object TravelModule {

  @Provides
  fun provideNewTravelDialogMapper(): NewTravelVehicleDialogMapper = NewTravelVehicleDialogMapperImpl()

  @Provides
  fun provideNewTravelScreenMapper(): NewTravelVehicleScreenMapper = NewTravelVehicleScreenMapperImpl()

  @Provides
  fun provideNewTravelDateDialogMapper(): NewTravelDateDialogMapper = NewTravelDateDialogMapperImpl()

  @Provides
  fun provideNewTravelDateScreenMapper(): NewTravelDateScreenMapper = NewTravelDateScreenMapperImpl()

  @Provides
  fun provideNewTravelDestinationDialogMapper(): NewTravelDestinationDialogMapper = NewTravelDestinationDialogMapperImpl()

  @Provides
  fun provideNewTravelDestinationScreenMapper(): NewTravelDestinationScreenMapper = NewTravelDestinationScreenMapperImpl()

  @Provides
  fun provideNewTravelOriginDialogMapper(): NewTravelOriginDialogMapper = NewTravelOriginDialogMapperImpl()

  @Provides
  fun provideNewTravelOriginScreenMapper(): NewTravelOriginScreenMapper = NewTravelOriginScreenMapperImpl()

  @Provides
  fun provideTravelDetailsScreenMapper(): TravelDetailsScreenMapper = TravelDetailsScreenMapperImpl()
}