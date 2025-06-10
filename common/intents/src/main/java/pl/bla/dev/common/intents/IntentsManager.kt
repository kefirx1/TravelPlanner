package pl.bla.dev.common.intents

import android.content.Intent
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.core.net.toUri
import pl.bla.dev.common.activityconnector.ActivityConnector

interface IntentsActivityConnector : ActivityConnector

interface IntentsManager {
  suspend fun startAppSettingsIntent()
}

class IntentsManagerImpl(): IntentsManager, IntentsActivityConnector {
  lateinit var activity: ComponentActivity

  override suspend fun startAppSettingsIntent() {
    activity.startActivity(
      Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        setData(("package:" + activity.packageName).toUri())
      }
    )
  }

  override fun connect(activity: ComponentActivity) {
    this.activity = activity
  }
}