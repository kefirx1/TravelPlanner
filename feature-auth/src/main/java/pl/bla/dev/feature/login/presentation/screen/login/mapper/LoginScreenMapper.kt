package pl.bla.dev.feature.login.presentation.screen.login.mapper

import pl.bla.dev.common.usecase.Mapper
import pl.bla.dev.feature.login.presentation.screen.login.LoginVM
import pl.bla.dev.feature.login.presentation.screen.login.mapper.LoginScreenMapper.Params

interface LoginScreenMapper : Mapper<Params, LoginVM.ScreenData> {
  data class Params(
    val state: LoginVM.State,
  )
}

class LoginScreenMapperImpl : LoginScreenMapper {
  override fun invoke(params: Params): LoginVM.ScreenData =
    when (params.state) {
      is LoginVM.State -> LoginVM.ScreenData.MainScreen(
        yes = "",
      )
    }
}