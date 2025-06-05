package pl.bla.dev.travelplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pl.bla.dev.common.activityconnector.ActivityConnector
import pl.bla.dev.common.ui.theming.TravelPlannerTheme
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var connectActivityUC: ActivityConnector

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