package pl.bla.dev.travelplanner.loader

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import pl.bla.dev.common.loader.LoaderManager

internal class LoaderManagerImpl: LoaderManager {
  private val loaderStatus: MutableSharedFlow<Boolean> = MutableSharedFlow()

  override fun visibilityMonitor(): Flow<Boolean> = loaderStatus

  override suspend fun showLoader() {
    loaderStatus.emit(true)
  }

  override suspend fun hideLoader() {
    loaderStatus.emit(false)
  }
}