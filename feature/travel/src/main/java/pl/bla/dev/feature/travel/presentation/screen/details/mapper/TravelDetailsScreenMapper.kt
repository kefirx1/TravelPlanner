package pl.bla.dev.feature.travel.presentation.screen.details.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.feature.travel.presentation.screen.details.TravelDetailsVM

interface TravelDetailsScreenMapper : Mapper<TravelDetailsScreenMapper.Params, TravelDetailsVM.ScreenData> {
  data class Params(
    val state: TravelDetailsVM.State,
    val onBackClick: () -> Unit,
  )
}

internal class TravelDetailsScreenMapperImpl : TravelDetailsScreenMapper {
  override fun invoke(params: TravelDetailsScreenMapper.Params): TravelDetailsVM.ScreenData =
    when (params.state) {
      is TravelDetailsVM.State.Initial -> TravelDetailsVM.ScreenData.Initial(
        onBackClick = params.onBackClick,
      )
      is TravelDetailsVM.State.Initialized -> TravelDetailsVM.ScreenData.Initialized(
        onBackClick = params.onBackClick,
      )
    }
}