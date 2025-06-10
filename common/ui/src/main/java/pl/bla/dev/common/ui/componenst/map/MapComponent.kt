package pl.bla.dev.common.ui.componenst.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.utsman.osmandcompose.CameraState
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.MapProperties
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberOverlayManagerState
import org.osmdroid.views.overlay.CopyrightOverlay

@Composable
fun MapComponent(
  cameraState: CameraState,
  onFirstLoadAction: () -> Unit,
) {
  val overlayManagerState = rememberOverlayManagerState()
  val context = LocalContext.current

  OpenStreetMap(
    cameraState = cameraState,
    overlayManagerState = overlayManagerState,
    onFirstLoadListener = {
      val copyright = CopyrightOverlay(context)
      overlayManagerState.overlayManager.add(copyright)
      onFirstLoadAction()
    },
    properties = DefaultMapProperties
      .copy(
        zoomButtonVisibility = ZoomButtonVisibility.NEVER,
      )
  )
}