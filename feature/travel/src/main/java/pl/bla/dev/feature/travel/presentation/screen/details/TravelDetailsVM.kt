package pl.bla.dev.feature.travel.presentation.screen.details

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.feature.settings.contract.domain.model.TravelFullData
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

    }

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

  }

  override fun mapScreenData(): TravelDetailsVM.ScreenData = travelDetailsScreenMapper(
    params = TravelDetailsScreenMapper.Params(
      state = state.value,
      onBackClick = {
        dispatchAction(TravelDetailsVM.Action.Back)
      }
    )
  )


  private fun dispatchAction(action: TravelDetailsVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        is TravelDetailsVM.State.Initial -> when (action) {
          is TravelDetailsVM.Action.Back -> TravelDetailsVM.Action.Navigation.Back.emit()
        }
        is TravelDetailsVM.State.Initialized -> when (action) {
          is TravelDetailsVM.Action.Back -> TravelDetailsVM.Action.Navigation.Back.emit()
        }
      }
    }
  }
}