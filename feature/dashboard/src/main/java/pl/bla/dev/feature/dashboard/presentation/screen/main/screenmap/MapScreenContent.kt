package pl.bla.dev.feature.dashboard.presentation.screen.main.screenmap

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.utsman.osmandcompose.CameraState
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.rememberMarkerState
import org.osmdroid.util.GeoPoint
import pl.bla.dev.common.ui.R
import pl.bla.dev.common.ui.componenst.map.MapComponent
import pl.bla.dev.common.ui.componenst.permissions.PermissionRequester
import pl.bla.dev.feature.dashboard.presentation.screen.main.MainDashboardVM

@Composable
fun MapScreenContent(
  cameraState: CameraState,
  mapHasAlreadyLoaded: MutableState<Boolean>,
  data: MainDashboardVM.ScreenData.MapScreen
) {
  val context = LocalContext.current
  when (data.permissionRequesterData) {
    null -> {
      val userMarker = rememberMarkerState(
        geoPoint = GeoPoint(data.currentLocation)
      )
      val userMarkerIcon: Drawable? by remember {
        mutableStateOf(context.getDrawable(R.drawable.outline_location_searching_24))
      }
      val travelMarkerIconOrigin: Drawable? by remember {
        mutableStateOf(context.getDrawable(R.drawable.outline_pin_drop_24))
      }
      val travelMarkerIconDestination: Drawable? by remember {
        mutableStateOf(context.getDrawable(R.drawable.outline_pin_drop_24_v2))
      }

      MapComponent(
        cameraState = cameraState,
        onFirstLoadAction = {
          if (data.currentLocation != null && !mapHasAlreadyLoaded.value) {
            mapHasAlreadyLoaded.value = true
            cameraState.geoPoint = GeoPoint(data.currentLocation)
          }
        }
      ) {
        Marker(
          icon = userMarkerIcon,
          state = userMarker
        )

        data.travelsMarkers.forEach { travelMarker ->
          val travelMarkerOrigin = rememberMarkerState(
            geoPoint = GeoPoint(travelMarker.latitudeOrigin, travelMarker.longitudeOrigin)
          )
          val travelMarkerDestination = rememberMarkerState(
            geoPoint = GeoPoint(travelMarker.latitudeDestination, travelMarker.longitudeDestination)
          )

          Marker(
            icon = travelMarkerIconOrigin,
            state = travelMarkerOrigin
          )
          Marker(
            icon = travelMarkerIconDestination,
            state = travelMarkerDestination
          )
        }
      }
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