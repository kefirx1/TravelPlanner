package pl.bla.dev.common.intents

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.ComponentActivity
import pl.bla.dev.common.activityconnector.ActivityConnector

interface IntentsActivityConnector : ActivityConnector

interface IntentsManager {
  fun startAppSettingsIntent()
}

class IntentsManagerImpl(): IntentsManager, IntentsActivityConnector {
  lateinit var activity: ComponentActivity

  override fun startAppSettingsIntent() {
    activity.startActivity(
      Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        setData(Uri.fromParts("package", activity.packageName, null))
      }
    )
  }

  override fun connect(activity: ComponentActivity) {
    this.activity = activity
  }
}