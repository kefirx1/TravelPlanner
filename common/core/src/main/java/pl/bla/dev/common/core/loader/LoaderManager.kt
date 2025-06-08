package pl.bla.dev.common.core.loader

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import pl.bla.dev.common.activityconnector.ActivityConnector

interface LoaderActivityConnector : ActivityConnector

interface LoaderManager {
  fun visibilityMonitor(): Flow<Boolean>
  suspend fun showLoader()
  suspend fun hideLoader()
}

internal class LoaderManagerImpl: LoaderManager, LoaderActivityConnector {
  private val loaderStatus: MutableSharedFlow<Boolean> = MutableSharedFlow()

  override fun visibilityMonitor(): Flow<Boolean> = loaderStatus

  override suspend fun showLoader() {
    loaderStatus.emit(true)
  }

  override suspend fun hideLoader() {
    loaderStatus.emit(false)
  }

  override fun connect(activity: ComponentActivity) {
    activity.setContent {
      Loader(visibility = loaderStatus)
    }

  }


}