package pl.bla.dev.feature.dashboard.presentation.screen.main.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
  val label: String,
  val onClick: (Int) -> Unit,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector,
)
