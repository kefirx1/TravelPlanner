package pl.bla.dev.feature.login.presentation.screen.personalinfo.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.ui.componenst.button.LargeButtonData
import pl.bla.dev.common.ui.componenst.input.TextFieldData
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.feature.login.presentation.screen.personalinfo.PersonalInfoVM

interface PersonalInfoScreenMapper : Mapper<PersonalInfoScreenMapper.Params, PersonalInfoVM.ScreenData> {
  data class Params(
    val state: PersonalInfoVM.State,
    val onBackClick: () -> Unit,
    val onNextClick: () -> Unit,
    val onEmailValueChanged: (String) -> Unit,
    val onEmailFocusChanged: (Boolean) -> Unit,
    val onNameValueChanged: (String) -> Unit,
    val onNameFocusChanged: (Boolean) -> Unit,
  )
}

class PersonalInfoScreenMapperImpl : PersonalInfoScreenMapper {
  override fun invoke(params: PersonalInfoScreenMapper.Params): PersonalInfoVM.ScreenData =
    when (params.state) {
      is PersonalInfoVM.State -> PersonalInfoVM.ScreenData(
        tabData = TopAppBarData.Back(
          onNavigationIconClick = params.onBackClick,
        ),
        screenDescription = "Podaj swoje dane, będą potrzebne do korzystania z aplikacji i komunikacji z innymi użytkownikami",
        nameInput = TextFieldData(
          initialText = params.state.typedName,
          label = "Wpisz swoje imię",
          onValueChanged = params.onNameValueChanged,
          validationState = params.state.typedNameState,
          onFocusChanged = params.onNameFocusChanged,
        ),
        emailInput = TextFieldData(
          initialText = params.state.typedEmail,
          label = "Wpisz email",
          onValueChanged = params.onEmailValueChanged,
          validationState = params.state.emailState,
          onFocusChanged = params.onEmailFocusChanged,
        ),
        onBackClick = params.onBackClick,
        nextButtonData = LargeButtonData.Primary(
          text = "Dalej",
          onClick = params.onNextClick,
        ),
      )
    }

}