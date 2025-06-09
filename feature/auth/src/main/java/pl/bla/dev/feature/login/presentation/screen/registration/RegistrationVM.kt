package pl.bla.dev.feature.login.presentation.screen.registration

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.core.viewmodel.CustomViewModelFactory
import pl.bla.dev.feature.login.presentation.AuthDestinations
import pl.bla.dev.feature.login.presentation.screen.registration.mapper.RegistrationScreenMapper

interface RegistrationVM {
  data class State(
    val userName: String,
  )

  sealed interface Action {
    sealed interface Navigation : Action {
      data object Back : Navigation
    }
    data object Back : Action
  }

  data class ScreenData(
    val yes: String,
    val onBackClick: () -> Unit,
  )

  val screenData: StateFlow<ScreenData>
}

@HiltViewModel(assistedFactory = RegistrationVMImpl.RegistrationVMFactory::class)
class RegistrationVMImpl @AssistedInject constructor(
  private val registrationScreenMapper: RegistrationScreenMapper,
  @Assisted val setupData: AuthDestinations.Registration,
) : CustomViewModel<RegistrationVM.State, RegistrationVM.ScreenData, RegistrationVM.Action.Navigation>(
  initialStateValue = RegistrationVM.State(userName = ""),
), RegistrationVM {
  @AssistedFactory
  interface RegistrationVMFactory: CustomViewModelFactory<AuthDestinations.Registration, RegistrationVMImpl> {
    override fun setup(setupData: AuthDestinations.Registration): RegistrationVMImpl
  }


  override val screenData: StateFlow<RegistrationVM.ScreenData> = _screenData

  init {
    initState()
  }

  fun dispatchAction(action: RegistrationVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        is RegistrationVM.State -> when (action) {
          is RegistrationVM.Action.Back -> RegistrationVM.Action.Navigation.Back.emit()
          else -> {}
        }
      }
    }
  }

  override suspend fun onStateEnter(newState: RegistrationVM.State) {
    when (newState) {
      is RegistrationVM.State -> {
      }
    }
  }

  override fun mapScreenData(): RegistrationVM.ScreenData = registrationScreenMapper(
    params = RegistrationScreenMapper.Params(
      state = state.value,
      onBackClick = { dispatchAction(RegistrationVM.Action.Back) }
    )
  )

}