package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.NewTravelOriginVM

interface NewTravelOriginScreenMapper : Mapper<NewTravelOriginScreenMapper.Params, NewTravelOriginVM.ScreenData> {
  data class Params(
    val state: NewTravelOriginVM.State,
    val onBackClick: () -> Unit,
    val onCloseProcessClick: () -> Unit,
    val onNextClick: () -> Unit,
  ): UseCase.Params
}

internal class NewTravelOriginScreenMapperImpl : NewTravelOriginScreenMapper {
  override fun invoke(params: NewTravelOriginScreenMapper.Params): NewTravelOriginVM.ScreenData =
    when (params.state) {
      NewTravelOriginVM.State.Initial -> NewTravelOriginVM.ScreenData(
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