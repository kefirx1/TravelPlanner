package pl.bla.dev.feature.dashboard.presentation.screen.main.screentravel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.bla.dev.be.backendservice.contract.domain.model.VehicleType
import pl.bla.dev.common.ui.componenst.card.ActionCard
import pl.bla.dev.common.ui.componenst.card.ActionCardData
import pl.bla.dev.common.ui.componenst.divider.Divider
import pl.bla.dev.common.ui.componenst.text.CustomText
import pl.bla.dev.feature.dashboard.presentation.screen.main.MainDashboardVM
import pl.bla.dev.feature.dashboard.presentation.screen.main.model.TravelShortDisplayData
import java.time.format.DateTimeFormatter

@Composable
fun TravelScreenContent(data: MainDashboardVM.ScreenData.TravelScreen) {

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(
        top = 60.dp,
      )
  ) {
    LazyColumn {
      item {
        if (data.currentTravels.isNotEmpty()) {
          CustomText(
            text = "Aktualnie trwające podróże",
            style = MaterialTheme.typography.titleLarge,
          )
          Spacer(modifier = Modifier.height(10.dp))
        }
      }
      items(data.currentTravels.size) { index ->
        TravelItem(travelShortDisplayData = data.currentTravels[index])
      }

      item {
        if (data.currentTravels.isNotEmpty()) {
          Divider(spacer = 10.dp)
        }
        if (data.futureTravels.isNotEmpty()) {
          Spacer(modifier = Modifier.height(10.dp))
          CustomText(
            text = "Przyszłe podróże",
            style = MaterialTheme.typography.titleLarge,
          )
          Spacer(modifier = Modifier.height(10.dp))
        }
      }
      items(data.futureTravels.size) { index ->
        TravelItem(travelShortDisplayData = data.futureTravels[index])
      }

      item {
        if (data.futureTravels.isNotEmpty()) {
          Divider(spacer = 10.dp)
        }
        if (data.pastTravels.isNotEmpty()) {
          Spacer(modifier = Modifier.height(10.dp))
          CustomText(
            text = "Zakończone podróże",
            style = MaterialTheme.typography.titleLarge,
          )
          Spacer(modifier = Modifier.height(10.dp))
        }
      }
      items(data.pastTravels.size) { index ->
        TravelItem(travelShortDisplayData = data.pastTravels[index])
      }

      item {
        if (data.cancelledTravels.isNotEmpty()) {
          if (data.pastTravels.isNotEmpty()) {
            Divider(spacer = 10.dp)
          }
          Spacer(modifier = Modifier.height(10.dp))
          CustomText(
            text = "Nieodbyte podróże",
            style = MaterialTheme.typography.titleLarge,
          )
          Spacer(modifier = Modifier.height(10.dp))
        }
      }
      items(data.cancelledTravels.size) { index ->
        TravelItem(travelShortDisplayData = data.cancelledTravels[index])
      }
    }
  }
}

@Composable
private fun TravelItem(travelShortDisplayData: TravelShortDisplayData) {
  val travelShortData = travelShortDisplayData.travelShortData

  ActionCard(
    data = ActionCardData(
      content = {
        CustomText(
          text = "${travelShortData.startDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))}",
          style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(5.dp))

        CustomText(
          text = "${getLocomotionLabel(vehicleType = travelShortData.originVehicleType)}: ${travelShortData.originCity} (${travelShortData.originCountry})",
        )
        Spacer(modifier = Modifier.height(5.dp))

        CustomText(
          text = "Miejsce docelowe: ${travelShortData.destinationCity} (${travelShortData.destinationCountry})",
        )
      },
      onClick = { travelShortDisplayData.onClick(travelShortData.travelId) },
    )
  )
  Spacer(modifier = Modifier.height(20.dp))
}

private fun getLocomotionLabel(vehicleType: VehicleType) = when (vehicleType) {
  VehicleType.PLANE -> "Lot z"
  VehicleType.TRAIN -> "Odjazd z"
  VehicleType.BUS -> "Odjazd z"
  VehicleType.CAR -> "Odjazd z"
}