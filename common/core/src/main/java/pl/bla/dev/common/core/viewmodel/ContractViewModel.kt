package pl.bla.dev.common.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class ContractViewModel : ViewModel() {
  private val contractState = MutableStateFlow<Map<Any, Any>>(emptyMap())

  fun setContractData(destination: Any, data: Any) {
    viewModelScope.launch {
      contractState.emit(contractState.value + (destination to data))
    }
  }
  fun <T> retrieveData(destination: Any): T? {
    return contractState.value[destination] as? T
  }
}