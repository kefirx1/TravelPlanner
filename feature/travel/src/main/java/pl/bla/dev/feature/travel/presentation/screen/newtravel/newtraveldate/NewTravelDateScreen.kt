package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.common.ui.componenst.basescaffold.BaseScaffold
import pl.bla.dev.common.ui.componenst.button.LargeButton
import pl.bla.dev.common.ui.componenst.picker.DatePickerInput
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
      Column(
        modifier = Modifier
          .padding(horizontal = 10.dp)
      ) {
        Spacer(modifier = Modifier.height(50.dp))

        CustomText(
          text = data.startDateLabel,
          style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))

        DatePickerInput(data = data.startDatePickerInputData)
        Spacer(modifier = Modifier.height(40.dp))

        CustomText(
          text = data.endDateLabel,
          style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))

        DatePickerInput(data = data.endDatePickerInputData)
        Spacer(modifier = Modifier.height(40.dp))
      }
    },
    bottomBar = {
      Column(
        modifier = Modifier.padding(
          horizontal = 40.dp,
          vertical = 10.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        LargeButton(buttonData = data.addNewButtonData)
      }
    },
  )
}