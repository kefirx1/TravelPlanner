package pl.bla.dev.feature.travel.presentation.screen.datepicker

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.ui.componenst.picker.CustomDatePickerData
import pl.bla.dev.feature.travel.presentation.screen.datepicker.TravelDatePickerVMImpl.DatePickerVMFactory

interface TravelDatePickerVM {
  data object State

  sealed interface Action {
    sealed interface Navigation : Action {
      data class OnDialogAction(
        val dialogAction: () -> Unit,
      ) : Navigation
    }
  }

  data class ScreenData(
    val customDatePickerData: CustomDatePickerData,
  )

  val screenData: StateFlow<ScreenData>
}

@HiltViewModel(assistedFactory = DatePickerVMFactory::class)
class TravelDatePickerVMImpl @AssistedInject constructor(
  @Assisted val setupData: CustomDatePickerData,
) : CustomViewModel<TravelDatePickerVM.State, TravelDatePickerVM.ScreenData, TravelDatePickerVM.Action.Navigation>(
  initialStateValue = TravelDatePickerVM.State,
), TravelDatePickerVM {
  @AssistedFactory
  interface DatePickerVMFactory: CustomViewModelFactory<CustomDatePickerData, TravelDatePickerVMImpl> {
    override fun setup(setupData: CustomDatePickerData) : TravelDatePickerVMImpl
  }

  override val screenData: StateFlow<TravelDatePickerVM.ScreenData> = _screenData

  override suspend fun onStateEnter(newState: TravelDatePickerVM.State) {}

  override fun mapScreenData(): TravelDatePickerVM.ScreenData =
    TravelDatePickerVM.ScreenData(
      customDatePickerData = setupData.copy(
        onDismiss = {
          TravelDatePickerVM.Action.Navigation.OnDialogAction(
            dialogAction = setupData.onDismiss
          ).emit()
        },
        onNewDatePicked = { newDate ->
          TravelDatePickerVM.Action.Navigation.OnDialogAction(
            dialogAction = {
              setupData.onNewDatePicked(newDate)
            }
          ).emit()
        }
      ),
    )
}