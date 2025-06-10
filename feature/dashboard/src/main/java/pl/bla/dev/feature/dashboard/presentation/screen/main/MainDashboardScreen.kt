package pl.bla.dev.feature.dashboard.presentation.screen.main

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utsman.osmandcompose.rememberCameraState
import org.osmdroid.util.GeoPoint
import pl.bla.dev.common.ui.componenst.basescaffold.BaseScaffold
import pl.bla.dev.common.ui.componenst.emptyscreen.EmptyScreen
import pl.bla.dev.common.ui.componenst.text.CustomText
import pl.bla.dev.common.ui.theming.AppColors
import pl.bla.dev.feature.dashboard.presentation.screen.main.screenmap.MapScreenContent
import pl.bla.dev.feature.dashboard.presentation.screen.main.screensettings.SettingsScreenContent
import pl.bla.dev.feature.dashboard.presentation.screen.main.screentravel.TravelScreenContent

private const val DEFAULT_MAP_SPEED = 0L
private const val DEFAULT_MAP_ZOOM = 12.0

@Composable
fun MainDashboardScreen(viewModel: MainDashboardVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()
  val mapHasAlreadyLoaded = remember { mutableStateOf(false) }
  val mapCameraState = rememberCameraState {
    speed = DEFAULT_MAP_SPEED
    zoom = DEFAULT_MAP_ZOOM
  }

  BackHandler {
    state.onBackClick()
  }

  BaseScaffold(
    topBar = {

    },
    content = {
      when (val screenData = state) {
        is MainDashboardVM.ScreenData.MapScreen -> MapScreenContent(
          cameraState = mapCameraState,
          data = screenData,
          mapHasAlreadyLoaded = mapHasAlreadyLoaded
        )
        is MainDashboardVM.ScreenData.TravelScreen -> TravelScreenContent(data = screenData)
        is MainDashboardVM.ScreenData.SettingsScreen -> SettingsScreenContent(data = screenData)
      }
    },
    bottomBar = {
      NavigationBar(
        containerColor = AppColors.black,
        contentColor = AppColors.white,
      ) {
        state.bottomNavItems.forEachIndexed { index, item ->
          NavigationBarItem(
            selected = index == state.selectedItem,
            onClick = { item.onClick(index) },
            icon = {
              Icon(
                imageVector = if (index == state.selectedItem) item.selectedIcon else item.unselectedIcon,
                contentDescription = item.label,
              )
            },
            label = {
              CustomText(
                text = item.label,
                style = MaterialTheme.typography.labelSmall,
              )
            },
          )
        }
      }
    }
  )
}
