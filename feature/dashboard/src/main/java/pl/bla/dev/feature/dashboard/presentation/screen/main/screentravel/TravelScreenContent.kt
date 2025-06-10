package pl.bla.dev.feature.dashboard.presentation.screen.main.screentravel

import androidx.compose.runtime.Composable
import pl.bla.dev.common.ui.componenst.text.CustomText
import pl.bla.dev.feature.dashboard.presentation.screen.main.MainDashboardVM

@Composable
fun TravelScreenContent(data: MainDashboardVM.ScreenData.TravelScreen) {
  CustomText(
    text = "TravelScreen",
  )
}