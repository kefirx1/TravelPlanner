package pl.bla.dev.feature.login.presentation.screen.onboarding

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContent
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentItem
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentSection
import pl.bla.dev.be.backendservice.contract.domain.usecase.GetOnboardingContentUC
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.core.usecase.fold
import pl.bla.dev.common.core.viewmodel.CustomViewModel
import pl.bla.dev.common.loader.domain.RunWithLoaderUC
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.chips.CustomFilterChipsListData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.login.presentation.screen.onboarding.mapper.OnboardingScreenMapper
import javax.inject.Inject

interface OnboardingVM {
  sealed interface State {
    data object Initial : State
    data class Initialized(
      val onboardingContent: OnboardingContent,
      val selectedChips: List<OnboardingContentItem> = emptyList(),
    ) : State
  }

  sealed interface Action {
    sealed interface Navigation : Action {
      data class ToRegistration(
        val selectedChips: List<OnboardingContentSection>,
      ) : Navigation

      data object Back : Navigation
    }
    data class ChipsSelected(
      val chips: OnboardingContentItem,
      val selected: Boolean,
    ) : Action
    data object ToRegistration : Action
    data object Back : Action
  }

  sealed interface ScreenData {
    data class Initial(
      val onBackClick: () -> Unit,
    ) : ScreenData
    data class Initialized(
      val topAppBarData: TopAppBarData.Back,
      val screenDescription: String,
      val screenDescriptionContext: String,
      val sectionsTitle: List<String>,
      val onboardingChips: List<CustomFilterChipsListData>,
      val nextButtonData: LargeButtonData.Primary,
      val onBackClick: () -> Unit,
    ) : ScreenData
  }

  val screenData: StateFlow<ScreenData>
}

@HiltViewModel
class OnboardingVMImpl @Inject constructor(
  private val onboardingScreenMapper: OnboardingScreenMapper,
  private val getOnboardingContentUC: GetOnboardingContentUC,
  private val runWithLoaderUC: RunWithLoaderUC,
) : CustomViewModel<OnboardingVM.State, OnboardingVM.ScreenData, OnboardingVM.Action.Navigation>(
  initialStateValue = OnboardingVM.State.Initial,
), OnboardingVM {
  override val screenData: StateFlow<OnboardingVM.ScreenData> = _screenData

  init {
    initState()
  }

  fun dispatchAction(action: OnboardingVM.Action) {
    viewModelScope.launch {
      when (val currentState = state.value) {
        is OnboardingVM.State.Initial -> when (action) {
          is OnboardingVM.Action.Back -> OnboardingVM.Action.Navigation.Back.emit()
          else -> {}
        }
        is OnboardingVM.State.Initialized -> when (action) {
          is OnboardingVM.Action.ChipsSelected -> {
            val selectedChips = currentState.selectedChips.toMutableList()
            if (action.selected) {
              selectedChips.add(action.chips)
            } else {
              selectedChips.remove(action.chips)
            }
            currentState.copy(
              selectedChips = selectedChips,
            ).mutate()
          }
          is OnboardingVM.Action.ToRegistration -> {
            OnboardingVM.Action.Navigation.ToRegistration(
              selectedChips = getSelectedSections(currentState = currentState)
            ).emit()
          }
          is OnboardingVM.Action.Back -> OnboardingVM.Action.Navigation.Back.emit()
          else -> {}
        }
      }
    }
  }

  override suspend fun onStateEnter(newState: OnboardingVM.State) {
    when (newState) {
      is OnboardingVM.State.Initial -> {
        runWithLoaderUC {
          getOnboardingContentUC(UseCase.Params.Empty).fold(
            onRight = { content ->
              OnboardingVM.State.Initialized(
                onboardingContent = content,
              ).override()
            },
            onLeft = {
              //TODO show error
            },
          )
        }
      }
      is OnboardingVM.State.Initialized -> {}
    }
  }

  override fun mapScreenData(): OnboardingVM.ScreenData = onboardingScreenMapper(
    params = OnboardingScreenMapper.Params(
      state = state.value,
      onBackClick = { dispatchAction(OnboardingVM.Action.Back) },
      onChipsSelect = { selected, chips ->
        dispatchAction(OnboardingVM.Action.ChipsSelected(selected = selected, chips = chips))
      },
      onNextClick = { dispatchAction(OnboardingVM.Action.ToRegistration) }
    )
  )

  private fun getSelectedSections(
    currentState: OnboardingVM.State.Initialized,
  ): List<OnboardingContentSection> =
    currentState.onboardingContent.content.mapNotNull { section ->
      section.content.mapNotNull { content ->
        content.takeIf { content.valueId in currentState.selectedChips.map { it.valueId } }
      }.let {
        if (it.isEmpty()) return@mapNotNull null

        OnboardingContentSection(
          sectionId = section.sectionId,
          title = section.title,
          content = it,
        )
      }
    }

}