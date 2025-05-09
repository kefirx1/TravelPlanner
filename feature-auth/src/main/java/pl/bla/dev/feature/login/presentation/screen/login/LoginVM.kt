package pl.bla.dev.feature.login.presentation.screen.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import pl.bla.dev.common.ui.viewmodel.CustomViewModel
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM.Action
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM.ScreenData
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM.State
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapper
import javax.inject.Inject

interface LoginVM {
  data class State(
    val yes: String,
  )

  sealed interface Action {
    sealed interface Navigation : Action {
      data object Back : Navigation
    }
  }

  sealed interface ScreenData {
    data class MainScreen(
      val yes: String,
    ) : ScreenData
  }

  val screenData: StateFlow<ScreenData>
}


@HiltViewModel
class LoginVMImpl @Inject constructor(
  private val loginScreenMapper: LoginScreenMapper,
) : CustomViewModel<State, ScreenData, Action.Navigation>(initialStateValue = State("")), LoginVM {

  override val screenData: StateFlow<ScreenData>
    get() = _screenData

  override fun mapScreenData() = loginScreenMapper(
    params = LoginScreenMapper.Params(state = state.value),
  )

}