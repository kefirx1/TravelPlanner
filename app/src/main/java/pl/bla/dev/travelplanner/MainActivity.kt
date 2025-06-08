package pl.bla.dev.travelplanner

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pl.bla.dev.common.activityconnector.ActivityConnector
import pl.bla.dev.common.ui.theming.AppColors
import pl.bla.dev.common.ui.theming.TravelPlannerTheme
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var connectActivityUC: ActivityConnector

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupSplashScreen()

    lifecycleScope.launch {
      connectActivityUC.connect(this@MainActivity)
    }

    enableEdgeToEdge(
      navigationBarStyle = SystemBarStyle.dark(AppColors.black.toArgb())
    )
    setContent {
      TravelPlannerTheme {
        MainAppNavGraph()
      }
    }
  }

  private fun setupSplashScreen() {
    splashScreen.setOnExitAnimationListener { splashScreenView ->
      val slideUp = ObjectAnimator.ofFloat(
        splashScreenView,
        View.TRANSLATION_Y,
        0f,
        -splashScreenView.height.toFloat()
      )
      slideUp.interpolator = AnticipateInterpolator()
      slideUp.duration = 500L
      slideUp.doOnEnd { splashScreenView.remove() }
      slideUp.start()
    }
  }

}