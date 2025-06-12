package pl.bla.dev.common.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.common.core.navigation.Destination

abstract class ContractViewModel : ViewModel() {
  private val contractState: MutableMap<Any, Any> = mutableMapOf()

  fun setContractData(destination: Destination, data: Any) {
    contractState[destination] = data
  }
  fun <T> retrieveData(destination: Destination): T? {
    return contractState.remove(destination) as? T
  }
}