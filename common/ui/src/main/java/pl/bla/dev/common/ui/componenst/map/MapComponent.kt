package pl.bla.dev.common.ui.componenst.map

import androidx.compose.runtime.Composable
import com.utsman.osmandcompose.CameraState
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState

@Composable
fun MapComponent(
  cameraState: CameraState
) {
  OpenStreetMap(
    cameraState = cameraState
  )
}