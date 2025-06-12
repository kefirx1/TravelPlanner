package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.mapper

import pl.bla.dev.be.backendservice.contract.domain.model.CountryConfig
import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.select.SelectData
import pl.bla.dev.common.ui.componenst.select.SelectItemData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.NewTravelOriginVM

interface NewTravelOriginScreenMapper : Mapper<NewTravelOriginScreenMapper.Params, NewTravelOriginVM.ScreenData> {
  data class Params(
    val state: NewTravelOriginVM.State,
    val newTravelConfig: NewTravelConfig,
    val onBackClick: () -> Unit,
    val onCloseProcessClick: () -> Unit,
    val onNextClick: () -> Unit,
    val onSelectCountry: (Int) -> Unit,
    val onSelectCity: (Int) -> Unit,
    val onSelectVehicle: (Int) -> Unit,
  ): UseCase.Params
}

internal class NewTravelOriginScreenMapperImpl : NewTravelOriginScreenMapper {
  override fun invoke(params: NewTravelOriginScreenMapper.Params): NewTravelOriginVM.ScreenData =
    when (params.state) {
      is NewTravelOriginVM.State.Initialized -> NewTravelOriginVM.ScreenData(
        topAppBarData = TopAppBarData.BackAndAction(
          onNavigationIconClick = params.onBackClick,
          onActionIconClick = params.onCloseProcessClick,
        ),
        nextButtonData = LargeButtonData.Primary(
          text = "Dalej",
          onClick = params.onNextClick,
        ),
        onBackClick = params.onBackClick,
        countryLabel = "Kraj z którego chcesz wyruszyć",
        cityLabel = "Miasto startowe",
        vehicleLabel = "Miejsce startu podróży",
        originCountrySelectData = SelectData(
          selectedOption = params.state.selectedOriginCountryId,
          onSelect = params.onSelectCountry,
          content = params.newTravelConfig.countriesConfig.getCountriesSelectItemData(),
        ),
        originCitySelectData = SelectData(
          selectedOption = params.state.selectedOriginCityId,
          onSelect = params.onSelectCity,
          content = params.newTravelConfig.countriesConfig.getCitiesSelectItemData(
            selectedCountryId = params.state.selectedOriginCountryId,
          ),
        ),
        originVehicleSelectData = SelectData(
          selectedOption = params.state.selectedOriginVehicleId,
          onSelect = params.onSelectVehicle,
          content = params.newTravelConfig.countriesConfig.getVehiclesSelectItemData(
            selectedCountryId = params.state.selectedOriginCountryId,
            selectedCityId = params.state.selectedOriginCityId,
          )
        ),
      )
    }

  private fun List<CountryConfig>.getCountriesSelectItemData() =
    this.map { country ->
      SelectItemData(
        id = country.countryId,
        label = country.countryName,
      )
    }

  private fun List<CountryConfig>.getCitiesSelectItemData(selectedCountryId: Int) =
    this.find { it.countryId == selectedCountryId }?.citiesConfig?.map { city ->
      SelectItemData(
        id = city.cityId,
        label = city.cityName,
      )
    } ?: emptyList()

  private fun List<CountryConfig>.getVehiclesSelectItemData(selectedCountryId: Int, selectedCityId: Int) =
    this.find { it.countryId == selectedCountryId }?.citiesConfig?.find { it.cityId == selectedCityId }?.vehiclesConfig?.map { vehicle ->
      SelectItemData(
        id = vehicle.vehicleId,
        label = vehicle.vehicleName,
      )
    } ?: emptyList()


}