package pl.bla.dev.feature.dashboard.presentation.screen.main.screenmap

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.utsman.osmandcompose.CameraState
import org.osmdroid.util.GeoPoint
import pl.bla.dev.common.ui.componenst.map.MapComponent
import pl.bla.dev.common.ui.componenst.permissions.PermissionRequester
import pl.bla.dev.feature.dashboard.presentation.screen.main.MainDashboardVM

@Composable
fun MapScreenContent(
  cameraState: CameraState,
  mapHasAlreadyLoaded: MutableState<Boolean>,
  data: MainDashboardVM.ScreenData.MapScreen
) {
  when (data.permissionRequesterData) {
    null -> {
      MapComponent(
        cameraState = cameraState,
        onFirstLoadAction = {
          if (data.currentLocation != null && !mapHasAlreadyLoaded.value) {
            mapHasAlreadyLoaded.value = true
            cameraState.geoPoint = GeoPoint(data.currentLocation)
          }
        }
      )
    }
    else -> Column(
      modifier = Modifier
        .fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      PermissionRequester(data = data.permissionRequesterData)
    }
  }

}