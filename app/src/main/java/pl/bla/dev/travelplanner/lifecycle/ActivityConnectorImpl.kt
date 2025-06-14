package pl.bla.dev.travelplanner.lifecycle

import androidx.appcompat.app.AppCompatActivity
import pl.bla.dev.common.activityconnector.ActivityConnector
import pl.bla.dev.common.biometric.BiometricPromptConnector
import pl.bla.dev.common.intents.IntentsActivityConnector
import pl.bla.dev.common.permission.PermissionsActivityConnector

internal class ActivityConnectorImpl(
  private val permissionsActivityConnector: PermissionsActivityConnector,
  private val intentsActivityConnector: IntentsActivityConnector,
  private val biometricPromptConnector: BiometricPromptConnector,
) : ActivityConnector {
  override fun connect(activity: AppCompatActivity) {
    listOf(
      permissionsActivityConnector,
      intentsActivityConnector,
      biometricPromptConnector,
    ).forEach { activityResultLauncher ->
      activityResultLauncher.connect(activity = activity)
    }
  }
}