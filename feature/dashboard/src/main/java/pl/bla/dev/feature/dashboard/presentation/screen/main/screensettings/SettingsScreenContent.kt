package pl.bla.dev.feature.dashboard.presentation.screen.main.screensettings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.bla.dev.common.ui.componenst.card.ActionCard
import pl.bla.dev.common.ui.componenst.card.ActionCardData
import pl.bla.dev.common.ui.componenst.text.CustomText
import pl.bla.dev.feature.dashboard.presentation.screen.main.MainDashboardVM

@Composable
fun SettingsScreenContent(data: MainDashboardVM.ScreenData.SettingsScreen) {
  Column(
    modifier = Modifier
      .verticalScroll(state = rememberScrollState())
      .fillMaxSize()
      .padding(
        top = 80.dp,
      )
  ) {
    ActionCard(
      data = ActionCardData(
        content = {
          CustomText(
            text = data.changePasswordLabel,
            style = MaterialTheme.typography.titleMedium,
          )
        },
        onClick = data.changePasswordClick,
      ),
    )
    Spacer(modifier = Modifier.height(10.dp))

    ActionCard(
      data = ActionCardData(
        content = {
          CustomText(
            text = data.biometricLabel,
            style = MaterialTheme.typography.titleMedium,
          )
        },
        onClick = data.biometricClick,
      ),
    )
    Spacer(modifier = Modifier.height(10.dp))
  }
}