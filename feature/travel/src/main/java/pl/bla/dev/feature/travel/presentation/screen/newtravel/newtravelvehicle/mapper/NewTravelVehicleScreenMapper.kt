package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.NewTravelVehicleVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper.NewTravelVehicleScreenMapper.Params

interface NewTravelVehicleScreenMapper : Mapper<Params, NewTravelVehicleVM.ScreenData> {
  data class Params(
    val state: NewTravelVehicleVM.State,
    val onBackClick: () -> Unit,
    val onNextClick: () -> Unit,
  ): UseCase.Params
}

internal class NewTravelVehicleScreenMapperImpl : NewTravelVehicleScreenMapper {
  override fun invoke(params: Params): NewTravelVehicleVM.ScreenData =
    when (params.state) {
      NewTravelVehicleVM.State.Initial -> NewTravelVehicleVM.ScreenData.Initial(
        topAppBarData = TopAppBarData.BackAndTitle(
          title = "Stwórz nową podróż",
          onNavigationIconClick = params.onBackClick,
        ),
        onBackClick = params.onBackClick,
      )
      is NewTravelVehicleVM.State.Initialized -> NewTravelVehicleVM.ScreenData.Initialized(
        topAppBarData = TopAppBarData.BackAndTitle(
          title = "Stwórz nową podróż",
          onNavigationIconClick = params.onBackClick,
        ),
        nextButtonData = LargeButtonData.Primary(
          text = "Dalej",
          onClick = params.onNextClick,
        ),
        onBackClick = params.onBackClick,
      )
    }

}