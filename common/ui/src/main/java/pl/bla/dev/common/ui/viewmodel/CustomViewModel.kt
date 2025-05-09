package pl.bla.dev.common.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class CustomViewModel<STATE, SCREEN_DATA, NAV_ACTION>(
  initialStateValue: STATE,
) : ViewModel() {
  val navAction: Channel<NAV_ACTION> = Channel()

  val _screenData: MutableStateFlow<SCREEN_DATA> = MutableStateFlow(mapScreenData())

  protected val _state: MutableStateFlow<STATE> = MutableStateFlow(initialStateValue)
  val state: StateFlow<STATE> = _state

  init {
    viewModelScope.launch {
      state.collect {
        _screenData.emit(mapScreenData())
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    viewModelScope.cancel()
  }

  abstract fun mapScreenData(): SCREEN_DATA

  fun NAV_ACTION.emit() = viewModelScope.launch {
    navAction.send(this@emit)
  }
}