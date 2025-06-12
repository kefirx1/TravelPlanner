package pl.bla.dev.feature.travel.presentation.screen.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.common.ui.componenst.basescaffold.BaseScaffold
import pl.bla.dev.common.ui.componenst.button.LargeButton
import pl.bla.dev.common.ui.componenst.card.BaseCard
import pl.bla.dev.common.ui.componenst.emptyscreen.EmptyScreen
import pl.bla.dev.common.ui.componenst.tab.CustomTopAppBar
import pl.bla.dev.common.ui.componenst.text.CustomText

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
  BaseScaffold(
    topBar = {
      CustomTopAppBar(topAppBarData = data.topAppBarData)
    },
    content = {
      Column(
        modifier = Modifier
          .verticalScroll(state = rememberScrollState())
      ) {
        Spacer(modifier = Modifier.height(20.dp))

        CustomText(
          text = data.originSection,
          style = MaterialTheme.typography.displaySmall,
          customSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))

        BaseCard {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(
                vertical = 10.dp,
                horizontal = 5.dp,
              )
          ) {
            CustomText(
              text = data.travelStartDate,
              style = MaterialTheme.typography.bodyLarge,
              customSize = 25.sp,
            )
            Spacer(modifier = Modifier.height(5.dp))

            CustomText(
              text = data.travelOrigin,
              style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(5.dp))

            CustomText(
              text = data.travelOriginVehicleName,
              style = MaterialTheme.typography.bodyMedium,
            )
            CustomText(
              text = data.travelOriginVehicleAddress,
              style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomText(
              text = data.travelDestination,
              style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(5.dp))

            CustomText(
              text = data.travelDestinationVehicleName,
              style = MaterialTheme.typography.bodyMedium,
            )
            CustomText(
              text = data.travelDestinationVehicleAddress,
              style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
          }
        }
        Spacer(modifier = Modifier.height(20.dp))

        CustomText(
          text = data.destinationSection,
          style = MaterialTheme.typography.displaySmall,
          customSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(5.dp))

        BaseCard {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(
                vertical = 10.dp,
                horizontal = 5.dp,
              )
          ) {
            CustomText(
              text = data.travelEndDate,
              style = MaterialTheme.typography.bodyLarge,
              customSize = 25.sp,
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomText(
              text = data.travelOriginReturn,
              style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(5.dp))

            CustomText(
              text = data.travelDestinationVehicleName,
              style = MaterialTheme.typography.bodyMedium,
            )
            CustomText(
              text = data.travelDestinationVehicleAddress,
              style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomText(
              text = data.travelDestinationReturn,
              style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(5.dp))

            CustomText(
              text = data.travelOriginVehicleName,
              style = MaterialTheme.typography.bodyMedium,
            )
            CustomText(
              text = data.travelOriginVehicleAddress,
              style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
          }
        }
        Spacer(modifier = Modifier.height(60.dp))

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
        data.cancelTravelButtonData?.let { button ->
          LargeButton(buttonData = button)
          Spacer(modifier = Modifier.height(10.dp))
        }

        LargeButton(buttonData = data.removeTravelButtonData)
      }
    },
  )
}