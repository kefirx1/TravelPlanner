package pl.bla.dev.common.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface CustomViewModelFactory <PARAM, VM> {
  fun setup(setupData: PARAM): VM
}

abstract class CustomViewModel<STATE, SCREEN_DATA, NAV_ACTION>(
  initialStateValue: STATE,
) : ViewModel() {
  protected val _screenData: MutableStateFlow<SCREEN_DATA> by lazy {
    MutableStateFlow(mapScreenData())
  }
  private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialStateValue)
  protected val state: StateFlow<STATE> = _state

  val navAction: MutableSharedFlow<NAV_ACTION> = MutableSharedFlow()

  init {
    viewModelScope.launch {
      state.collect {
        try {
          _screenData.emit(mapScreenData())
        } catch (_: NullPointerException) { }
      }
    }
  }

  abstract fun mapScreenData(): SCREEN_DATA

  fun STATE.override() {
    viewModelScope.launch {
      _state.emit(this@override)
    }
  }

  fun NAV_ACTION.emit() = viewModelScope.launch {
    navAction.emit(this@emit)
  }
}