package pl.bla.dev.travelplanner.lifecycle

import androidx.activity.ComponentActivity
import pl.bla.dev.common.activityconnector.ActivityConnector
import pl.bla.dev.common.intents.IntentsActivityConnector
import pl.bla.dev.common.permission.PermissionsActivityConnector

internal class ActivityConnectorImpl(
  private val permissionsActivityConnector: PermissionsActivityConnector,
  private val intentsActivityConnector: IntentsActivityConnector,
) : ActivityConnector {
  override fun connect(activity: ComponentActivity) {
    listOf(
      permissionsActivityConnector,
      intentsActivityConnector,
    ).forEach { activityResultLauncher ->
      activityResultLauncher.connect(activity = activity)
    }
  }
}