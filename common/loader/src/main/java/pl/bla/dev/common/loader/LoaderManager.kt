package pl.bla.dev.common.loader

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface LoaderManager {
  fun visibilityMonitor(): Flow<Boolean>
  suspend fun showLoader()
  suspend fun hideLoader()
}

