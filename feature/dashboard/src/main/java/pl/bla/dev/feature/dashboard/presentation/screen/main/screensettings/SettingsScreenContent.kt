package pl.bla.dev.feature.dashboard.presentation.screen.main.screensettings

import androidx.compose.runtime.Composable
import pl.bla.dev.common.ui.componenst.text.CustomText
import pl.bla.dev.feature.dashboard.presentation.screen.main.MainDashboardVM

@Composable
fun SettingsScreenContent(data: MainDashboardVM.ScreenData.SettingsScreen) {
  CustomText(
    text = "SettingsScreen",
  )
}