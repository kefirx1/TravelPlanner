package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleType
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.common.ui.componenst.select.SelectData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.NewTravelDateVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.NewTravelDestinationVM.NewTravelSetupData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.mapper.NewTravelDestinationDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.mapper.NewTravelDestinationDialogMapper.DialogType
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.mapper.NewTravelDestinationScreenMapper

interface NewTravelDestinationVM {
  sealed interface State {
    data class Initialized(
      val selectedDestinationCountryId: Int = 1,
      val selectedDestinationCityId: Int? = null,
      val selectedDestinationVehicleId: Int? = null,
      val selectedDestinationVehicleType: VehicleType,
    ) : State
  }

  sealed interface Action {
    sealed interface Navigation {
      data object Back : Navigation
      data object CloseProcess : Navigation
      data class ShowDialog(
        val dialogData: DialogData,
      ) : Navigation
      data class ToDate(
        val setupData: NewTravelDateVM.NewTravelSetupData,
      ) : Navigation
    }

    data class UpdateSelectedDestinationCountry(
      val countryId: Int,
    ) : Action
    data class UpdateSelectedDestinationCity(
      val cityId: Int,
    ) : Action
    data class UpdateSelectedDestinationVehicle(
      val vehicleId: Int,
    ) : Action
    data object OnNextClick : Action
    data class ShowDialog(
      val dialogType: DialogType,
    ) : Action
    data object CloseProcess : Action
    data object Back : Action
  }

  data class ScreenData(
    val topAppBarData: TopAppBarData,
    val nextButtonData: LargeButtonData.Primary,
    val countryLabel: String,
    val destinationCountrySelectData: SelectData,
    val cityLabel: String,
    val destinationCitySelectData: SelectData,
    val vehicleLabel: String,
    val destinationVehicleSelectData: SelectData,
    val onBackClick: () -> Unit,
  )

  val screenData: StateFlow<ScreenData>

  data class NewTravelSetupData(
    val newTravelConfig: NewTravelConfig,
    val originVehicleType: VehicleType,
    val originCityId: Int,
    val originCountryId: Int,
    val originVehicleId: Int,
  )
}


@HiltViewModel(assistedFactory = NewTravelDestinationVMImpl.NewTravelDestinationVMFactory::class)
class NewTravelDestinationVMImpl @AssistedInject constructor(
  private val newTravelDestinationScreenMapper: NewTravelDestinationScreenMapper,
  private val newTravelDestinationDialogMapper: NewTravelDestinationDialogMapper,
  @Assisted val setupData: NewTravelSetupData,
) : CustomViewModel<NewTravelDestinationVM.State, NewTravelDestinationVM.ScreenData, NewTravelDestinationVM.Action.Navigation>(
  initialStateValue = NewTravelDestinationVM.State.Initialized(selectedDestinationVehicleType = setupData.originVehicleType),
), NewTravelDestinationVM {

  @AssistedFactory
  interface NewTravelDestinationVMFactory: CustomViewModelFactory<NewTravelSetupData, NewTravelDestinationVMImpl> {
    override fun setup(setupData: NewTravelSetupData): NewTravelDestinationVMImpl
  }

  override val screenData: StateFlow<NewTravelDestinationVM.ScreenData> = _screenData

  init {
    initState()
  }

  override suspend fun onStateEnter(newState: NewTravelDestinationVM.State) {
    when (newState) {
      is NewTravelDestinationVM.State.Initialized -> {}
    }
  }

  override fun mapScreenData(): NewTravelDestinationVM.ScreenData = newTravelDestinationScreenMapper(
    params = NewTravelDestinationScreenMapper.Params(
      state = state.value,
      selectedOriginVehicleType = setupData.originVehicleType,
      selectedOriginVehicleId = setupData.originVehicleId,
      newTravelConfig = setupData.newTravelConfig,
      onBackClick = {
        dispatchAction(NewTravelDestinationVM.Action.Back)
      },
      onCloseProcessClick = {
        dispatchAction(NewTravelDestinationVM.Action.ShowDialog(dialogType = DialogType.Close))
      },
      onNextClick = {
        dispatchAction(NewTravelDestinationVM.Action.OnNextClick)
      },
      onSelectCountry = { countryId ->
        dispatchAction(NewTravelDestinationVM.Action.UpdateSelectedDestinationCountry(countryId = countryId))
      },
      onSelectCity = { cityId ->
        dispatchAction(NewTravelDestinationVM.Action.UpdateSelectedDestinationCity(cityId = cityId))
      },
      onSelectVehicle = { vehicleId ->
        dispatchAction(NewTravelDestinationVM.Action.UpdateSelectedDestinationVehicle(vehicleId = vehicleId))
      }
    ),
  )

  private fun dispatchAction(action: NewTravelDestinationVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        is NewTravelDestinationVM.State.Initialized -> when (action) {
          is NewTravelDestinationVM.Action.ShowDialog -> NewTravelDestinationVM.Action.Navigation.ShowDialog(
            dialogData = newTravelDestinationDialogMapper(
              params = NewTravelDestinationDialogMapper.Params(
                type = action.dialogType,
                onCloseClick = { dispatchAction(NewTravelDestinationVM.Action.CloseProcess) }
              ),
            ),
          ).emit()
          NewTravelDestinationVM.Action.OnNextClick -> NewTravelDestinationVM.Action.Navigation.ToDate(
            setupData = NewTravelDateVM.NewTravelSetupData(
              newTravelConfig = setupData.newTravelConfig,
              originVehicleType = setupData.originVehicleType,
              originCityId = setupData.originCityId,
              originCountryId = setupData.originCountryId,
              originVehicleId = setupData.originVehicleId,
              destinationVehicleType = currentState.selectedDestinationVehicleType,
              destinationCityId = currentState.selectedDestinationCityId ?: return@launch,
              destinationCountryId = currentState.selectedDestinationCountryId,
              destinationVehicleId = currentState.selectedDestinationVehicleId ?: return@launch,
            ),
          ).emit()
          is NewTravelDestinationVM.Action.UpdateSelectedDestinationCountry -> currentState.copy(
            selectedDestinationCountryId = action.countryId,
            selectedDestinationCityId = null,
            selectedDestinationVehicleId = null,
          ).mutate()
          is NewTravelDestinationVM.Action.UpdateSelectedDestinationCity -> currentState.copy(
            selectedDestinationCityId = action.cityId,
            selectedDestinationVehicleId = null,
          ).mutate()
          is NewTravelDestinationVM.Action.UpdateSelectedDestinationVehicle -> currentState.copy(
            selectedDestinationVehicleId = action.vehicleId,
          ).mutate()
          NewTravelDestinationVM.Action.CloseProcess -> NewTravelDestinationVM.Action.Navigation.CloseProcess.emit()
          NewTravelDestinationVM.Action.Back -> NewTravelDestinationVM.Action.Navigation.Back.emit()
        }
      }
    }
  }

}