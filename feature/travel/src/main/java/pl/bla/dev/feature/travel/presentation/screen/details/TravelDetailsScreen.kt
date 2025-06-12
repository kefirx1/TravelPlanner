package pl.bla.dev.feature.travel.presentation.screen.details

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.common.ui.componenst.emptyscreen.EmptyScreen

@Composable
fun TravelDetailsScreen(viewModel: TravelDetailsVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()

  when (val screenData = state) {
    is TravelDetailsVM.ScreenData.Initial -> EmptyScreen()
    is TravelDetailsVM.ScreenData.Initialized -> TravelDetailsScreenContent(data = screenData)
  }

  BackHandler {
    state.onBackClick()
  }
}

@Composable
fun TravelDetailsScreenContent(
  data: TravelDetailsVM.ScreenData.Initialized,
) {

}