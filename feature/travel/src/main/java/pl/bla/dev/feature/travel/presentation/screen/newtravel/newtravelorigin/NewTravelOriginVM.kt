package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin

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
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.NewTravelDestinationVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.NewTravelOriginVM.NewTravelSetupData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.mapper.NewTravelOriginDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.mapper.NewTravelOriginDialogMapper.DialogType
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.mapper.NewTravelOriginScreenMapper

interface NewTravelOriginVM {
  sealed interface State {
    data class Initialized(
      val selectedOriginCountryId: Int = 1,
      val selectedOriginCityId: Int = 101,
      val selectedOriginVehicleId: Int = 1001,
    ) : State
  }

  sealed interface Action {
    sealed interface Navigation {
      data object Back : Navigation
      data object CloseProcess : Navigation
      data class ShowDialog(
        val dialogData: DialogData,
      ) : Navigation
      data class ToDestination(
        val setupData: NewTravelDestinationVM.NewTravelSetupData,
      ) : Navigation
    }

    data class UpdateSelectedOriginCountry(
      val countryId: Int,
    ) : Action
    data class UpdateSelectedOriginCity(
      val cityId: Int,
    ) : Action
    data class UpdateSelectedOriginVehicle(
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
    val originCountrySelectData: SelectData,
    val cityLabel: String,
    val originCitySelectData: SelectData,
    val vehicleLabel: String,
    val originVehicleSelectData: SelectData,
    val onBackClick: () -> Unit,
  )

  val screenData: StateFlow<ScreenData>

  data class NewTravelSetupData(
    val newTravelConfig: NewTravelConfig,
    val originVehicleType: VehicleType,
  )
}


@HiltViewModel(assistedFactory = NewTravelOriginVMImpl.NewTravelOriginVMFactory::class)
class NewTravelOriginVMImpl @AssistedInject constructor(
  private val newTravelOriginScreenMapper: NewTravelOriginScreenMapper,
  private val newTravelOriginDialogMapper: NewTravelOriginDialogMapper,
  @Assisted val setupData: NewTravelSetupData,
) : CustomViewModel<NewTravelOriginVM.State, NewTravelOriginVM.ScreenData, NewTravelOriginVM.Action.Navigation>(
  initialStateValue = NewTravelOriginVM.State.Initialized(),
), NewTravelOriginVM {

  @AssistedFactory
  interface NewTravelOriginVMFactory: CustomViewModelFactory<NewTravelSetupData, NewTravelOriginVMImpl> {
    override fun setup(setupData: NewTravelSetupData): NewTravelOriginVMImpl
  }

  override val screenData: StateFlow<NewTravelOriginVM.ScreenData> = _screenData

  init {
    initState()
  }

  override suspend fun onStateEnter(newState: NewTravelOriginVM.State) {
    when (newState) {
      is NewTravelOriginVM.State.Initialized -> {}
    }
  }

  override fun mapScreenData(): NewTravelOriginVM.ScreenData = newTravelOriginScreenMapper(
    params = NewTravelOriginScreenMapper.Params(
      state = state.value,
      newTravelConfig = setupData.newTravelConfig,
      onBackClick = {
        dispatchAction(NewTravelOriginVM.Action.Back)
      },
      onCloseProcessClick = {
        dispatchAction(NewTravelOriginVM.Action.ShowDialog(dialogType = DialogType.Close))
      },
      onNextClick = {
        dispatchAction(NewTravelOriginVM.Action.OnNextClick)
      },
      onSelectCountry = { countryId ->
        dispatchAction(NewTravelOriginVM.Action.UpdateSelectedOriginCountry(countryId = countryId))
      },
      onSelectCity = { cityId ->
        dispatchAction(NewTravelOriginVM.Action.UpdateSelectedOriginCity(cityId = cityId))
      },
      onSelectVehicle = { vehicleId ->
        dispatchAction(NewTravelOriginVM.Action.UpdateSelectedOriginVehicle(vehicleId = vehicleId))
      }
    ),
  )

  private fun dispatchAction(action: NewTravelOriginVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        is NewTravelOriginVM.State.Initialized -> when (action) {
          is NewTravelOriginVM.Action.ShowDialog -> NewTravelOriginVM.Action.Navigation.ShowDialog(
            dialogData = newTravelOriginDialogMapper(
              params = NewTravelOriginDialogMapper.Params(
                type = action.dialogType,
                onCloseClick = { dispatchAction(NewTravelOriginVM.Action.CloseProcess) }
              ),
            ),
          ).emit()
          NewTravelOriginVM.Action.OnNextClick -> NewTravelOriginVM.Action.Navigation.ToDestination(
            setupData = NewTravelDestinationVM.NewTravelSetupData(
              newTravelConfig = setupData.newTravelConfig,
              originVehicleType = setupData.originVehicleType,
              originCityId = currentState.selectedOriginCityId,
              originCountryId = currentState.selectedOriginCountryId,
              originVehicleId = currentState.selectedOriginVehicleId,
            ),
          ).emit()
          is NewTravelOriginVM.Action.UpdateSelectedOriginCountry -> currentState.copy(
            selectedOriginCountryId = action.countryId,
          ).mutate()
          is NewTravelOriginVM.Action.UpdateSelectedOriginCity -> currentState.copy(
            selectedOriginCityId = action.cityId,
          ).mutate()
          is NewTravelOriginVM.Action.UpdateSelectedOriginVehicle -> currentState.copy(
            selectedOriginVehicleId = action.vehicleId,
          ).mutate()
          NewTravelOriginVM.Action.CloseProcess -> NewTravelOriginVM.Action.Navigation.CloseProcess.emit()
          NewTravelOriginVM.Action.Back -> NewTravelOriginVM.Action.Navigation.Back.emit()
        }
      }
    }
  }

}