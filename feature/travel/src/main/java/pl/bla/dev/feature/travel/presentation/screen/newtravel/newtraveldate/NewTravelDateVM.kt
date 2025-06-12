package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleType
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.common.ui.componenst.picker.CustomDatePickerData
import pl.bla.dev.common.ui.componenst.picker.DatePickerInputData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.settings.contract.domain.usecase.SaveNewTravelUC
import pl.bla.dev.feature.travel.presentation.screen.details.TravelDetailsVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.NewTravelDateVM.NewTravelSetupData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.mapper.NewTravelDateDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.mapper.NewTravelDateDialogMapper.DialogType
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.mapper.NewTravelDateScreenMapper
import java.time.LocalDateTime

interface NewTravelDateVM {
  sealed interface State {
    data class Initialized(
      val startDate: LocalDateTime = LocalDateTime.now(),
      val endDate: LocalDateTime = LocalDateTime.now(),
    ) : State
  }

  sealed interface Action {
    sealed interface Navigation {
      data object Back : Navigation
      data object CloseProcess : Navigation
      data class ShowDialog(
        val dialogData: DialogData,
      ) : Navigation
      data class ToDetails(
        val setupData: TravelDetailsVM.TravelDetailsSetupData,
      ) : Navigation
      data class ShowDatePicker(
        val customDatePickerData: CustomDatePickerData,
      ) : Navigation
    }

    data class UpdateStartDate(
      val newDate: LocalDateTime,
    ) : Action
    data class UpdateEndDate(
      val newDate: LocalDateTime,
    ) : Action
    data class ShowDatePicker(
      val customDatePickerData: CustomDatePickerData,
    ) : Action
    data object AddNewTravel : Action
    data class ShowDialog(
      val dialogType: DialogType,
    ) : Action
    data object CloseProcess : Action
    data object Back : Action
  }

  data class ScreenData(
    val topAppBarData: TopAppBarData,
    val startDateLabel: String,
    val endDateLabel: String,
    val startDatePickerInputData: DatePickerInputData,
    val endDatePickerInputData: DatePickerInputData,
    val addNewButtonData: LargeButtonData.Primary,
    val onBackClick: () -> Unit,
  )

  val screenData: StateFlow<ScreenData>

  data class NewTravelSetupData(
    val newTravelConfig: NewTravelConfig,
    val originVehicleType: VehicleType,
    val originCityId: Int,
    val originCountryId: Int,
    val originVehicleId: Int,
    val destinationVehicleType: VehicleType,
    val destinationCityId: Int,
    val destinationCountryId: Int,
    val destinationVehicleId: Int,
  )
}


@HiltViewModel(assistedFactory = NewTravelDateVMImpl.NewTravelDateVMFactory::class)
class NewTravelDateVMImpl @AssistedInject constructor(
  private val newTravelDateScreenMapper: NewTravelDateScreenMapper,
  private val newTravelDateDialogMapper: NewTravelDateDialogMapper,
  private val saveNewTravelUC: SaveNewTravelUC,
  @Assisted val setupData: NewTravelSetupData,
) : CustomViewModel<NewTravelDateVM.State, NewTravelDateVM.ScreenData, NewTravelDateVM.Action.Navigation>(
  initialStateValue = NewTravelDateVM.State.Initialized(),
), NewTravelDateVM {

  @AssistedFactory
  interface NewTravelDateVMFactory: CustomViewModelFactory<NewTravelSetupData, NewTravelDateVMImpl> {
    override fun setup(setupData: NewTravelSetupData): NewTravelDateVMImpl
  }

  override val screenData: StateFlow<NewTravelDateVM.ScreenData> = _screenData

  init {
    initState()
  }

  override suspend fun onStateEnter(newState: NewTravelDateVM.State) {
    when (newState) {
      is NewTravelDateVM.State.Initialized -> {}
    }
  }

  override fun mapScreenData(): NewTravelDateVM.ScreenData = newTravelDateScreenMapper(
    params = NewTravelDateScreenMapper.Params(
      state = state.value,
      onBackClick = {
        dispatchAction(NewTravelDateVM.Action.Back)
      },
      onCloseProcessClick = {
        dispatchAction(NewTravelDateVM.Action.ShowDialog(dialogType = DialogType.Close))
      },
      onAddNewClick = {
        dispatchAction(NewTravelDateVM.Action.AddNewTravel)
      },
      onShowDatePickerClick = { pickerData ->
        dispatchAction(
          NewTravelDateVM.Action.ShowDatePicker(
            customDatePickerData = pickerData,
          )
        )
      },
      onStartDateSelect = { newDate ->
        dispatchAction(NewTravelDateVM.Action.UpdateStartDate(newDate = newDate))
      },
      onEndDateSelect = { newDate ->
        dispatchAction(NewTravelDateVM.Action.UpdateEndDate(newDate = newDate))
      }
    ),
  )

  private fun dispatchAction(action: NewTravelDateVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        is NewTravelDateVM.State.Initialized -> when (action) {
          is NewTravelDateVM.Action.ShowDialog -> NewTravelDateVM.Action.Navigation.ShowDialog(
            dialogData = newTravelDateDialogMapper(
              params = NewTravelDateDialogMapper.Params(
                type = action.dialogType,
                onCloseClick = { dispatchAction(NewTravelDateVM.Action.CloseProcess) }
              ),
            ),
          ).emit()

          is NewTravelDateVM.Action.AddNewTravel -> {
            saveNewTravelUC(
              param = SaveNewTravelUC.Params(
                originCountryId = setupData.originCountryId,
                destinationCountryId = setupData.destinationCountryId,
                originCityId = setupData.originCityId,
                destinationCityId = setupData.destinationCityId,
                originVehicleId = setupData.originVehicleId,
                destinationVehicleId = setupData.destinationVehicleId,
                startDate = currentState.startDate,
                endDate = currentState.endDate,
              )
            ).fold(
              onRight = { newTravelId ->
                NewTravelDateVM.Action.Navigation.ToDetails(
                  setupData = TravelDetailsVM.TravelDetailsSetupData(
                    travelId = newTravelId,
                  )
                ).emit()
              },
              onLeft = { error ->
                //TODO error handling
              }
            )
          }
          is NewTravelDateVM.Action.UpdateStartDate -> {
            currentState.copy(
              startDate = action.newDate,
            ).mutate()
          }
          is NewTravelDateVM.Action.UpdateEndDate -> {
            currentState.copy(
              endDate = action.newDate,
            ).mutate()
          }
          is NewTravelDateVM.Action.ShowDatePicker ->
            NewTravelDateVM.Action.Navigation.ShowDatePicker(customDatePickerData = action.customDatePickerData).emit()
          NewTravelDateVM.Action.CloseProcess -> NewTravelDateVM.Action.Navigation.CloseProcess.emit()
          NewTravelDateVM.Action.Back -> NewTravelDateVM.Action.Navigation.Back.emit()
        }
      }
    }
  }

}