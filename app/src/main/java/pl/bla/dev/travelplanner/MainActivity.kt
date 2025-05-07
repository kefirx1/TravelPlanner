package pl.bla.dev.travelplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pl.bla.dev.common.activityconnector.ActivityConnector
import pl.bla.dev.common.intents.domain.usecase.OpenAppSettingsIntentUC
import pl.bla.dev.common.permission.PermissionsManager
import pl.bla.dev.common.ui.theming.TravelPlannerTheme
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var connectActivityUC: ActivityConnector

  @Inject
  lateinit var permissionsManager: PermissionsManager

  @Inject
  lateinit var openAppSettingsIntentUC: OpenAppSettingsIntentUC

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launch {
      connectActivityUC.connect(this@MainActivity)
    }

    enableEdgeToEdge()
    setContent {
      TravelPlannerTheme {
        MainAppNavGraph()
      }
    }
  }

}