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

  private var stateAlreadyChanged = false

  fun initState() {
    viewModelScope.launch {
      state.collect { newState ->
        try {
          if (!stateAlreadyChanged) {
            onStateEnter(newState = newState)
            stateAlreadyChanged = true
          }
          _screenData.emit(mapScreenData())
        } catch (_: NullPointerException) { }
      }
    }
  }

  abstract suspend fun onStateEnter(newState: STATE)

  abstract fun mapScreenData(): SCREEN_DATA

//  Use to change to new state
  fun STATE.override() {
    viewModelScope.launch {
      stateAlreadyChanged = false
      _state.emit(this@override)
    }
  }

//  Use to update in current state
  fun STATE.mutate() {
    viewModelScope.launch {
      stateAlreadyChanged = true
      _state.emit(this@mutate)
    }
  }

  fun NAV_ACTION.emit() = viewModelScope.launch {
    navAction.emit(this@emit)
  }
}