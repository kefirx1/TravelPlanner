package pl.bla.dev.feature.login.presentation.screen.registration

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.common.ui.componenst.basescaffold.BaseScaffold
import pl.bla.dev.common.ui.componenst.tab.CustomTopAppBar
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData

@Composable
fun RegistrationScreen(viewModel: RegistrationVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()

  when (val screenData = state) {
    is RegistrationVM.ScreenData -> RegistrationScreenContent(
      data = screenData,
    )
  }
}

@Composable
fun RegistrationScreenContent(
  data: RegistrationVM.ScreenData,
) {
  BackHandler {
    data.onBackClick()
  }

  BaseScaffold(
    topBar = {
      CustomTopAppBar(
        topAppBarData = TopAppBarData.Back(
          onNavigationIconClick = data.onBackClick,
        )
      )
    },
    content = {

    }
  )

}