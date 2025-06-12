package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination

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
import pl.bla.dev.common.ui.componenst.select.Select
import pl.bla.dev.common.ui.componenst.tab.CustomTopAppBar
import pl.bla.dev.common.ui.componenst.text.CustomText

@Composable
fun NewTravelDestinationScreen(viewModel: NewTravelDestinationVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()

  when (val screenData = state) {
    is NewTravelDestinationVM.ScreenData -> NewTravelDestinationScreenContent(data = screenData)
  }
}

@Composable
fun NewTravelDestinationScreenContent(
  data: NewTravelDestinationVM.ScreenData,
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
          text = data.countryLabel,
          style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Select(data = data.destinationCountrySelectData)
        Spacer(modifier = Modifier.height(40.dp))

        CustomText(
          text = data.cityLabel,
          style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Select(data = data.destinationCitySelectData)
        Spacer(modifier = Modifier.height(40.dp))

        CustomText(
          text = data.vehicleLabel,
          style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Select(data = data.destinationVehicleSelectData)
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
        LargeButton(buttonData = data.nextButtonData)
      }
    },
  )
}