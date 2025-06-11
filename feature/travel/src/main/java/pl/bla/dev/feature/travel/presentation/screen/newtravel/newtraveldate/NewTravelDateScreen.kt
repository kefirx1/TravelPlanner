package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.common.ui.componenst.basescaffold.BaseScaffold
import pl.bla.dev.common.ui.componenst.tab.CustomTopAppBar
import pl.bla.dev.common.ui.componenst.text.CustomText

@Composable
fun NewTravelDateScreen(viewModel: NewTravelDateVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()

  when (val screenData = state) {
    is NewTravelDateVM.ScreenData -> NewTravelDateScreenContent(data = screenData)
  }
}

@Composable
fun NewTravelDateScreenContent(
  data: NewTravelDateVM.ScreenData,
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
        text = "D"
      )

    },
    bottomBar = {

    },
  )
}