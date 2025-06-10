package pl.bla.dev.feature.dashboard.presentation.screen.main.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.feature.dashboard.presentation.screen.main.MainDashboardVM
import pl.bla.dev.feature.dashboard.presentation.screen.main.mapper.MainDashboardScreenMapper.Params
import pl.bla.dev.feature.dashboard.presentation.screen.main.model.BottomNavItem

interface MainDashboardScreenMapper : Mapper<Params, MainDashboardVM.ScreenData> {
  data class Params(
    val state: MainDashboardVM.State,
    val onBackClick: () -> Unit,
    val onBottomNavItemClick: (Int) -> Unit,
  )
}

class MainDashboardScreenMapperImpl : MainDashboardScreenMapper {
  override fun invoke(params: Params): MainDashboardVM.ScreenData =
    when (params.state) {
      is MainDashboardVM.State.Initial -> MainDashboardVM.ScreenData.Initial(
        onBackClick = params.onBackClick,
      )
      is MainDashboardVM.State.MapScreen -> MainDashboardVM.ScreenData.MapScreen(
        bottomNavItems = getBottomItemsNav(onClick = params.onBottomNavItemClick),
        onBackClick = params.onBackClick,
      )
      is MainDashboardVM.State.TravelScreen -> MainDashboardVM.ScreenData.TravelScreen(
        bottomNavItems = getBottomItemsNav(onClick = params.onBottomNavItemClick),
        onBackClick = params.onBackClick,
      )
      is MainDashboardVM.State.SettingsScreen -> MainDashboardVM.ScreenData.SettingsScreen(
        bottomNavItems = getBottomItemsNav(onClick = params.onBottomNavItemClick),
        onBackClick = params.onBackClick,
      )
    }

  private fun getBottomItemsNav(onClick: (Int) -> Unit): List<BottomNavItem> = listOf(
    BottomNavItem(
      label = "Przeglądaj",
      onClick = onClick,
      selectedIcon = Icons.Filled.Search,
      unselectedIcon = Icons.Outlined.Search,
    ),
    BottomNavItem(
      label = "Pordóże",
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