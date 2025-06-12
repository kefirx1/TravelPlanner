package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleType
import pl.bla.dev.common.ui.componenst.basescaffold.BaseScaffold
import pl.bla.dev.common.ui.componenst.button.LargeButton
import pl.bla.dev.common.ui.componenst.emptyscreen.EmptyScreen
import pl.bla.dev.common.ui.componenst.select.Select
import pl.bla.dev.common.ui.componenst.select.SelectData
import pl.bla.dev.common.ui.componenst.select.SelectItemData
import pl.bla.dev.common.ui.componenst.tab.CustomTopAppBar
import pl.bla.dev.common.ui.componenst.text.CustomText

@Composable
fun NewTravelVehicleScreen(viewModel: NewTravelVehicleVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()

  BackHandler {
    state.onBackClick()
  }

  when (val screenData = state) {
    is NewTravelVehicleVM.ScreenData.Initial -> EmptyScreen()
    is NewTravelVehicleVM.ScreenData.Initialized -> NewTravelVehicleScreenContent(data = screenData)
  }
}

@Composable
fun NewTravelVehicleScreenContent(
  data: NewTravelVehicleVM.ScreenData.Initialized,
) {
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
          text = data.screenTitle,
          style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Select(data = data.vehicleTypeSelectData)
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
    }
  )
}