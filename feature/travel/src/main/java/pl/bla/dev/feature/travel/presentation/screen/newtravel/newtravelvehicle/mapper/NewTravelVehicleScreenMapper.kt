package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.select.SelectData
import pl.bla.dev.common.ui.componenst.select.SelectItemData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.NewTravelVehicleVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper.NewTravelVehicleScreenMapper.Params

interface NewTravelVehicleScreenMapper : Mapper<Params, NewTravelVehicleVM.ScreenData> {
  data class Params(
    val state: NewTravelVehicleVM.State,
    val onBackClick: () -> Unit,
    val onNextClick: () -> Unit,
    val onSelectVehicle: (Int) -> Unit,
  ): UseCase.Params
}

internal class NewTravelVehicleScreenMapperImpl : NewTravelVehicleScreenMapper {
  override fun invoke(params: Params): NewTravelVehicleVM.ScreenData =
    when (params.state) {
      NewTravelVehicleVM.State.Initial -> NewTravelVehicleVM.ScreenData.Initial(
        topAppBarData = TopAppBarData.BackAndTitle(
          title = "Nowa wycieczka",
          onNavigationIconClick = params.onBackClick,
        ),
        onBackClick = params.onBackClick,
      )
      is NewTravelVehicleVM.State.Initialized -> NewTravelVehicleVM.ScreenData.Initialized(
        topAppBarData = TopAppBarData.BackAndTitle(
          title = "Nowa wycieczka",
          onNavigationIconClick = params.onBackClick,
        ),
        nextButtonData = LargeButtonData.Primary(
          text = "Dalej",
          onClick = params.onNextClick,
        ),
        screenTitle = "Wybierz rodzaj pojazdu, którym wyruszysz w podróż",
        vehicleTypeSelectData = SelectData(
          content = listOf(
            SelectItemData(
              id = 0,
              label = "Samolotem",
            ),
            SelectItemData(
              id = 1,
              label = "Samochodem",
            ),
            SelectItemData(
              id = 2,
              label = "Pociągiem",
            ),
            SelectItemData(
              id = 3,
              label = "Autokarem",
            ),
          ),
          onSelect = params.onSelectVehicle,
          selectedOption = params.state.selectedOriginVehicleType?.ordinal ?: 0,
        ),
        onBackClick = params.onBackClick,
      )
    }

}