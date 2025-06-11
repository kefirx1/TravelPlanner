package pl.bla.dev.feature.dashboard.presentation.screen.main.model

import pl.bla.dev.feature.settings.contract.domain.model.TravelShortData

data class TravelShortDisplayData(
  val travelShortData: TravelShortData,
  val onClick: (String) -> Unit,
)
