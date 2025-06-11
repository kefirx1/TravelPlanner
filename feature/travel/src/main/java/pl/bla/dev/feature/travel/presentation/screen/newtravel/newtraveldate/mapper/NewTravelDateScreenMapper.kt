package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.NewTravelDateVM

interface NewTravelDateScreenMapper : Mapper<NewTravelDateScreenMapper.Params, NewTravelDateVM.ScreenData> {
  data class Params(
    val state: NewTravelDateVM.State,
    val onBackClick: () -> Unit,
    val onCloseProcessClick: () -> Unit,
  ): UseCase.Params
}

internal class NewTravelDateScreenMapperImpl : NewTravelDateScreenMapper {
  override fun invoke(params: NewTravelDateScreenMapper.Params): NewTravelDateVM.ScreenData =
    when (params.state) {
      NewTravelDateVM.State.Initial -> NewTravelDateVM.ScreenData(
        topAppBarData = TopAppBarData.BackAndAction(
          onNavigationIconClick = params.onBackClick,
          onActionIconClick = params.onCloseProcessClick,
        ),
        onBackClick = params.onBackClick,
      )
    }

}