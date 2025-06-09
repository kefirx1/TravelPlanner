package pl.bla.dev.feature.login.presentation.screen.onboarding.mapper

import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentItem
import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.chips.CustomFilterChipsData
import pl.bla.dev.common.ui.componenst.chips.CustomFilterChipsListData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.login.presentation.screen.onboarding.OnboardingVM
import pl.bla.dev.feature.login.presentation.screen.onboarding.mapper.OnboardingScreenMapper.Params

interface OnboardingScreenMapper : Mapper<Params, OnboardingVM.ScreenData> {
  data class Params(
    val state: OnboardingVM.State,
    val onChipsSelect: (Boolean, OnboardingContentItem) -> Unit,
    val onNextClick: () -> Unit,
    val onBackClick: () -> Unit,
  )
}

class OnboardingScreenMapperImpl : OnboardingScreenMapper {
  override fun invoke(params: Params): OnboardingVM.ScreenData =
    when (params.state) {
      is OnboardingVM.State.Initial -> OnboardingVM.ScreenData.Initial(
        onBackClick = params.onBackClick,
      )
      is OnboardingVM.State.Initialized -> OnboardingVM.ScreenData.Initialized(
        topAppBarData = TopAppBarData.Back(
          onNavigationIconClick = params.onBackClick,
        ),
        screenDescription = "Witaj w aplikacji TravelPlanner, w celu spersonalizowania aplikacji zaznacz interesujące Cie opcje!",
        screenDescriptionContext = "(Możesz wybrać tyle opcji ile chcesz)",
        sectionsTitle = params.state.onboardingContent.content.map { it.title },
        onboardingChips = params.state.onboardingContent.content.map { content ->
          CustomFilterChipsListData(
            chipsList = content.content.map { single ->
              CustomFilterChipsData(
                chipsText = single.label,
                onChipsSelect = { selected -> params.onChipsSelect(selected, single) },
                selected = params.state.selectedChips.contains(single)
              )
            },
            wrap = true,
          )
        },
        nextButtonData = LargeButtonData.Primary(
          text = "Dalej",
          onClick = params.onNextClick,
        ),
        onBackClick = params.onBackClick,
      )
    }

}