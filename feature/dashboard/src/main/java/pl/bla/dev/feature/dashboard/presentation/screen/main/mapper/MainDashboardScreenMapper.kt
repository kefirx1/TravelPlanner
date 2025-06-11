package pl.bla.dev.feature.dashboard.presentation.screen.main.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.permission.domain.model.PermissionResult
import pl.bla.dev.common.ui.componenst.button.SmallButtonData
import pl.bla.dev.common.ui.componenst.permissions.PermissionRequesterData
import pl.bla.dev.feature.dashboard.presentation.screen.main.MainDashboardVM
import pl.bla.dev.feature.dashboard.presentation.screen.main.mapper.MainDashboardScreenMapper.Params
import pl.bla.dev.feature.dashboard.presentation.screen.main.model.BottomNavItem
import pl.bla.dev.feature.dashboard.presentation.screen.main.model.TravelShortDisplayData
import pl.bla.dev.feature.settings.contract.domain.model.TravelStatus

interface MainDashboardScreenMapper : Mapper<Params, MainDashboardVM.ScreenData> {
  data class Params(
    val state: MainDashboardVM.State,
    val onBackClick: () -> Unit,
    val onBottomNavItemClick: (Int) -> Unit,
    val onOpenAppSettings: () -> Unit,
    val onRequestPermission: () -> Unit,
    val onTravelClick: (String) -> Unit,
    val onFABClick: () -> Unit,
  )
}

class MainDashboardScreenMapperImpl : MainDashboardScreenMapper {
  override fun invoke(params: Params): MainDashboardVM.ScreenData =
    when (params.state) {
      is MainDashboardVM.State.MapScreen -> MainDashboardVM.ScreenData.MapScreen(
        bottomNavItems = getBottomItemsNav(onClick = params.onBottomNavItemClick),
        onBackClick = params.onBackClick,
        currentLocation = params.state.currentLocation,
        permissionRequesterData = getPermissionRequesterData(
          foreverDenied = when (params.state.permissionResult) {
            PermissionResult.DENIED_FOREVER -> true
            else -> false
          },
          openAppSettings = params.onOpenAppSettings,
          requestPermission = params.onRequestPermission,
        ).takeIf { params.state.permissionResult != PermissionResult.GRANTED },
        onFABClick = params.onFABClick,
      )
      is MainDashboardVM.State.TravelScreen -> MainDashboardVM.ScreenData.TravelScreen(
        bottomNavItems = getBottomItemsNav(onClick = params.onBottomNavItemClick),
        onBackClick = params.onBackClick,
        futureTravels = params.state.travelsShortData
          .filter { travel -> travel.travelStatus == TravelStatus.FUTURE }
          .map { travel ->
            TravelShortDisplayData(
              travelShortData = travel,
              onClick = params.onTravelClick,
            )
          },
        pastTravels = params.state.travelsShortData
          .filter { travel -> travel.travelStatus == TravelStatus.PAST }
          .map { travel ->
            TravelShortDisplayData(
              travelShortData = travel,
              onClick = params.onTravelClick,
            )
          },
        cancelledTravels = params.state.travelsShortData
          .filter { travel -> travel.travelStatus == TravelStatus.CANCELLED }
          .map { travel ->
            TravelShortDisplayData(
              travelShortData = travel,
              onClick = params.onTravelClick,
            )
          },
        currentTravels = params.state.travelsShortData
          .filter { travel -> travel.travelStatus == TravelStatus.CURRENT }
          .map { travel ->
            TravelShortDisplayData(
              travelShortData = travel,
              onClick = params.onTravelClick,
            )
          },
      )
      is MainDashboardVM.State.SettingsScreen -> MainDashboardVM.ScreenData.SettingsScreen(
        bottomNavItems = getBottomItemsNav(onClick = params.onBottomNavItemClick),
        onBackClick = params.onBackClick,
      )
    }

  private fun getPermissionRequesterData(
    foreverDenied: Boolean,
    openAppSettings: () -> Unit,
    requestPermission: () -> Unit,
  ) = PermissionRequesterData(
    label = "Aby użyć map potrzebujesz zezwolić na lokalizację",
    isDeniedForever = foreverDenied,
    onOpenSettingsClick = openAppSettings,
    requestPermissionButtonData = SmallButtonData.Secondary(
      text = "Zezwól na lokalizację",
      onClick = requestPermission,
    )
  )

  private fun getBottomItemsNav(onClick: (Int) -> Unit): List<BottomNavItem> = listOf(
    BottomNavItem(
      label = "Przeglądaj",
      onClick = onClick,
      selectedIcon = Icons.Filled.Search,
      unselectedIcon = Icons.Outlined.Search,
    ),
    BottomNavItem(
      label = "Podróże",
      onClick = onClick,
      selectedIcon = Icons.Filled.DateRange,
      unselectedIcon = Icons.Outlined.DateRange,
    ),
    BottomNavItem(
      label = "Ustawienia",
      onClick = onClick,
      selectedIcon = Icons.Filled.Settings,
      unselectedIcon = Icons.Outlined.Settings,
    ),
  )
}