package pl.bla.dev.feature.travel.presentation.screen.details

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.common.loader.domain.RunWithLoaderUC
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.settings.contract.domain.model.TravelFullData
import pl.bla.dev.feature.settings.contract.domain.usecase.CancelTravelUC
import pl.bla.dev.feature.settings.contract.domain.usecase.GetFullTravelDataUC
import pl.bla.dev.feature.settings.contract.domain.usecase.RemoveTravelUC
import pl.bla.dev.feature.settings.contract.domain.usecase.RestoreTravelUC
import pl.bla.dev.feature.travel.presentation.screen.details.mapper.TravelDetailsDialogMapper
import pl.bla.dev.feature.travel.presentation.screen.details.mapper.TravelDetailsScreenMapper

interface TravelDetailsVM {
  sealed interface State {
    data object Initial : State
    data class Initialized(
      val travelDetails: TravelFullData,
    ) : State
  }

  sealed interface Action {
    sealed interface Navigation {
      data object Back : Navigation
      data class ShowDialog(
        val dialogData: DialogData,
      ) : Navigation
    }

    data class ShowDialog(
      val dialogType: TravelDetailsDialogMapper.DialogType,
    ) : Action
    data object RemoveTravel : Action
    data object RestoreTravel : Action
    data object CancelTravel : Action
    data object Back : Action
  }

  sealed class ScreenData(
    open val onBackClick: () -> Unit,
  ) {
    data class Initial(
      override val onBackClick: () -> Unit,
    ) : ScreenData(
      onBackClick = onBackClick,
    )

    data class Initialized(
      val topAppBarData: TopAppBarData,
      val originSection: String,
      val destinationSection: String,
      val travelStartDate: String,
      val travelEndDate: String,
      val travelOrigin: String,
      val travelDestination: String,
      val travelOriginReturn: String,
      val travelDestinationReturn: String,
      val travelOriginVehicleName: String,
      val travelOriginVehicleAddress: String,
      val travelDestinationVehicleName: String,
      val travelDestinationVehicleAddress: String,
      val cancelTravelButtonData: LargeButtonData.Secondary?,
      val removeTravelButtonData: LargeButtonData.Tertiary,
      override val onBackClick: () -> Unit,
    ) : ScreenData(
      onBackClick = onBackClick,
    )
  }

  val screenData: StateFlow<ScreenData>

  data class TravelDetailsSetupData(
    val travelId: Int,
  )
}

@HiltViewModel(assistedFactory = TravelDetailsVMImpl.TravelDetailsVMFactory::class)
class TravelDetailsVMImpl @AssistedInject constructor(
  private val travelDetailsScreenMapper: TravelDetailsScreenMapper,
  private val getFullTravelDataUC: GetFullTravelDataUC,
  private val runWithLoaderUC: RunWithLoaderUC,
  private val travelDetailsDialogMapper: TravelDetailsDialogMapper,
  private val removeTravelUC: RemoveTravelUC,
  private val cancelTravelUC: CancelTravelUC,
  private val restoreTravelUC: RestoreTravelUC,
  @Assisted val setupData: TravelDetailsVM.TravelDetailsSetupData,
) : CustomViewModel<TravelDetailsVM.State, TravelDetailsVM.ScreenData, TravelDetailsVM.Action.Navigation>(
  initialStateValue = TravelDetailsVM.State.Initial,
), TravelDetailsVM {

  @AssistedFactory
  interface TravelDetailsVMFactory: CustomViewModelFactory<TravelDetailsVM.TravelDetailsSetupData, TravelDetailsVMImpl> {
    override fun setup(setupData: TravelDetailsVM.TravelDetailsSetupData): TravelDetailsVMImpl
  }

  override val screenData: StateFlow<TravelDetailsVM.ScreenData> = _screenData

  init {
    initState()
  }

  override suspend fun onStateEnter(newState: TravelDetailsVM.State) {
    when (newState) {
      is TravelDetailsVM.State.Initial -> {
        runWithLoaderUC {
          getFullTravelDataUC(
            param = GetFullTravelDataUC.Params(
              travelId = setupData.travelId,
            )
          ).fold(
            onRight = { travel ->
              TravelDetailsVM.State.Initialized(
                travelDetails = travel,
              ).override()
            },
            onLeft = {
              //TODO error handling
            }
          )
        }
      }
      is TravelDetailsVM.State.Initialized -> {}
    }
  }

  override fun mapScreenData(): TravelDetailsVM.ScreenData = travelDetailsScreenMapper(
    params = TravelDetailsScreenMapper.Params(
      state = state.value,
      onBackClick = {
        dispatchAction(TravelDetailsVM.Action.Back)
      },
      onCancelClick = {
        dispatchAction(TravelDetailsVM.Action.ShowDialog(
          dialogType = TravelDetailsDialogMapper.DialogType.Cancel,
        ))
      },
      onRemoveClick = {
        dispatchAction(TravelDetailsVM.Action.ShowDialog(
          dialogType = TravelDetailsDialogMapper.DialogType.Remove,
        ))
      },
      onRestoreClick = {
        dispatchAction(TravelDetailsVM.Action.ShowDialog(
          dialogType = TravelDetailsDialogMapper.DialogType.Restore,
        ))
      }
    )
  )


  private fun dispatchAction(action: TravelDetailsVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        is TravelDetailsVM.State.Initial -> when (action) {
          is TravelDetailsVM.Action.Back -> TravelDetailsVM.Action.Navigation.Back.emit()
          is TravelDetailsVM.Action.RestoreTravel -> {}
          is TravelDetailsVM.Action.CancelTravel -> {}
          is TravelDetailsVM.Action.RemoveTravel -> {}
          is TravelDetailsVM.Action.ShowDialog -> {}
        }
        is TravelDetailsVM.State.Initialized -> when (action) {
          is TravelDetailsVM.Action.Back -> TravelDetailsVM.Action.Navigation.Back.emit()
          is TravelDetailsVM.Action.RestoreTravel -> {
            restoreTravelUC(
              param = RestoreTravelUC.Params(
                travelId = currentState.travelDetails.travelId,
              ),
            ).fold(
              onRight = {
                TravelDetailsVM.State.Initial.override()
              },
              onLeft = {
                //TODO error handling
              }
            )
          }
          is TravelDetailsVM.Action.CancelTravel -> {
            cancelTravelUC(
              param = CancelTravelUC.Params(
                travelId = currentState.travelDetails.travelId,
              ),
            ).fold(
              onRight = {
                TravelDetailsVM.State.Initial.override()
              },
              onLeft = {
                //TODO error handling
              }
            )
          }
          is TravelDetailsVM.Action.RemoveTravel -> {
            removeTravelUC(
              param = RemoveTravelUC.Params(
                travelId = currentState.travelDetails.travelId,
              ),
            ).fold(
              onRight = {
                dispatchAction(TravelDetailsVM.Action.Back)
              },
              onLeft = {
                //TODO error handling
              }
            )
          }
          is TravelDetailsVM.Action.ShowDialog -> TravelDetailsVM.Action.Navigation.ShowDialog(
            dialogData = travelDetailsDialogMapper(
              params = TravelDetailsDialogMapper.Params(
                type = action.dialogType,
                onCancelTravelClick = {
                  dispatchAction(TravelDetailsVM.Action.CancelTravel)
                },
                onRemoveTravelClick = {
                  dispatchAction(TravelDetailsVM.Action.RemoveTravel)
                },
                onRestoreTravelClick = {
                  dispatchAction(TravelDetailsVM.Action.RestoreTravel)
                },
              ),
            ),
          ).emit()
        }
      }
    }
  }
}