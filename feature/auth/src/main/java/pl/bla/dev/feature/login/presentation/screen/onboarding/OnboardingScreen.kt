package pl.bla.dev.feature.login.presentation.screen.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.common.ui.R
import pl.bla.dev.common.ui.componenst.basescaffold.BaseScaffold
import pl.bla.dev.common.ui.componenst.button.LargeButton
import pl.bla.dev.common.ui.componenst.card.BaseCard
import pl.bla.dev.common.ui.componenst.chips.CustomFilterChips
import pl.bla.dev.common.ui.componenst.chips.CustomFilterChipsData
import pl.bla.dev.common.ui.componenst.divider.Divider
import pl.bla.dev.common.ui.componenst.emptyscreen.EmptyScreen
import pl.bla.dev.common.ui.componenst.tab.CustomTopAppBar
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.common.ui.componenst.text.CustomText

@Composable
fun OnboardingScreen(viewModel: OnboardingVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()

  when (val screenData = state) {
    is OnboardingVM.ScreenData.Initial -> {
      BackHandler {
        screenData.onBackClick()
      }

      EmptyScreen()
    }
    is OnboardingVM.ScreenData.Initialized -> OnboardingScreenContent(
      data = screenData,
    )
  }
}

@Composable
fun OnboardingScreenContent(
  data: OnboardingVM.ScreenData.Initialized,
) {
  BackHandler {
    data.onBackClick()
  }

  BaseScaffold(
    topBar = {
      CustomTopAppBar(topAppBarData = data.topAppBarData)
    },
    content = {
      CustomText(
        text = data.screenDescription,
        style = MaterialTheme.typography.headlineSmall,
      )
      Spacer(modifier = Modifier.height(5.dp))

      CustomText(
        text = data.screenDescriptionContext,
        style = MaterialTheme.typography.bodyMedium,
      )
      Spacer(modifier = Modifier.height(50.dp))

      LazyColumn {
        items(data.sectionsTitle.size) {
          CustomText(
            text = data.sectionsTitle[it],
            style = MaterialTheme.typography.titleMedium,
          )
          Spacer(modifier = Modifier.height(10.dp))

          CustomFilterChips(chipsList = data.onboardingChips[it])

          Divider(spacer = 8.dp)
        }
      }
    },
    bottomBar = {
      Column(
        modifier = Modifier.padding(
          horizontal = 20.dp,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        LargeButton(buttonData = data.nextButtonData)
      }
    }
  )

}