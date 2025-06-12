package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.mapper

import pl.bla.dev.be.backendservice.contract.domain.model.CountryConfig
import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleType
import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.select.SelectData
import pl.bla.dev.common.ui.componenst.select.SelectItemData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.NewTravelDestinationVM

interface NewTravelDestinationScreenMapper : Mapper<NewTravelDestinationScreenMapper.Params, NewTravelDestinationVM.ScreenData> {
  data class Params(
    val state: NewTravelDestinationVM.State,
    val selectedOriginVehicleType: VehicleType,
    val selectedOriginVehicleId: Int,
    val newTravelConfig: NewTravelConfig,
    val onBackClick: () -> Unit,
    val onCloseProcessClick: () -> Unit,
    val onNextClick: () -> Unit,
    val onSelectCountry: (Int) -> Unit,
    val onSelectCity: (Int) -> Unit,
    val onSelectVehicle: (Int) -> Unit,
  ): UseCase.Params
}

internal class NewTravelDestinationScreenMapperImpl : NewTravelDestinationScreenMapper {
  override fun invoke(params: NewTravelDestinationScreenMapper.Params): NewTravelDestinationVM.ScreenData =
    when (params.state) {
      is NewTravelDestinationVM.State.Initialized -> {
        val availableCities = params.newTravelConfig.countriesConfig.getCitiesSelectItemData(
          selectedCountryId = params.state.selectedDestinationCountryId,
        )
        val availableVehicles = params.newTravelConfig.countriesConfig.getVehiclesSelectItemData(
          selectedCountryId = params.state.selectedDestinationCountryId,
          selectedCityId = params.state.selectedDestinationCityId ?: availableCities.first().id,
          selectedOriginVehicleId = params.selectedOriginVehicleId,
          selectedOriginVehicleType = params.selectedOriginVehicleType,
        )
        NewTravelDestinationVM.ScreenData(
          topAppBarData = TopAppBarData.BackAndAction(
            onNavigationIconClick = params.onBackClick,
            onActionIconClick = params.onCloseProcessClick,
          ),
          nextButtonData = LargeButtonData.Primary(
            text = "Dalej",
            onClick = params.onNextClick,
          ),
          onBackClick = params.onBackClick,
          countryLabel = "Kraj docelowy",
          cityLabel = "Miasto docelowe",
          vehicleLabel = "Miejsce docelowe podróży",
          destinationCountrySelectData = SelectData(
            selectedOption = params.state.selectedDestinationCountryId,
            onSelect = params.onSelectCountry,
            content = params.newTravelConfig.countriesConfig.getCountriesSelectItemData(),
          ),
          destinationCitySelectData = SelectData(
            selectedOption = params.state.selectedDestinationCityId ?: -1,
            onSelect = params.onSelectCity,
            content = availableCities,
          ),
          destinationVehicleSelectData = SelectData(
            selectedOption = params.state.selectedDestinationVehicleId ?: -1,
            onSelect = params.onSelectVehicle,
            content = availableVehicles
          ),
        )
      }
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

  private fun List<CountryConfig>.getVehiclesSelectItemData(
    selectedOriginVehicleType: VehicleType,
    selectedOriginVehicleId: Int,
    selectedCountryId: Int,
    selectedCityId: Int,
  ) = this.find { it.countryId == selectedCountryId }?.citiesConfig
    ?.find { it.cityId == selectedCityId }?.vehiclesConfig
    ?.filter { it.vehicleType == selectedOriginVehicleType && it.vehicleId != selectedOriginVehicleId }
    ?.map { vehicle ->
      SelectItemData(
        id = vehicle.vehicleId,
        label = vehicle.vehicleName,
      )
    } ?: emptyList()

}