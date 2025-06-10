package pl.bla.dev.feature.dashboard.presentation.screen.main.screenmap

import androidx.compose.runtime.Composable
import com.utsman.osmandcompose.CameraState
import pl.bla.dev.common.ui.componenst.map.MapComponent
import pl.bla.dev.feature.dashboard.presentation.screen.main.MainDashboardVM

@Composable
fun MapScreenContent(
  cameraState: CameraState,
  data: MainDashboardVM.ScreenData.MapScreen
) {
  MapComponent(
    cameraState = cameraState
  )
}