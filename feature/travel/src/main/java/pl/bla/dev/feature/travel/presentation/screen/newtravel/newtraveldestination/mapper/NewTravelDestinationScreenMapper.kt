package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.NewTravelDestinationVM

interface NewTravelDestinationScreenMapper : Mapper<NewTravelDestinationScreenMapper.Params, NewTravelDestinationVM.ScreenData> {
  data class Params(
    val state: NewTravelDestinationVM.State,
    val onBackClick: () -> Unit,
    val onCloseProcessClick: () -> Unit,
    val onNextClick: () -> Unit,
  ): UseCase.Params
}

internal class NewTravelDestinationScreenMapperImpl : NewTravelDestinationScreenMapper {
  override fun invoke(params: NewTravelDestinationScreenMapper.Params): NewTravelDestinationVM.ScreenData =
    when (params.state) {
      NewTravelDestinationVM.State.Initial -> NewTravelDestinationVM.ScreenData(
        topAppBarData = TopAppBarData.BackAndAction(
          onNavigationIconClick = params.onBackClick,
          onActionIconClick = params.onCloseProcessClick,
        ),
        nextButtonData = LargeButtonData.Primary(
          text = "Dalej",
          onClick = params.onNextClick,
        ),
        onBackClick = params.onBackClick,
      )
    }

}