package pl.bla.dev.feature.login.presentation.screen.registration.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.feature.login.presentation.screen.registration.RegistrationVM
import pl.bla.dev.feature.login.presentation.screen.registration.mapper.RegistrationScreenMapper.Params

interface RegistrationScreenMapper : Mapper<Params, RegistrationVM.ScreenData> {
  data class Params(
    val state: RegistrationVM.State,
    val onBackClick: () -> Unit,
  )
}

class RegistrationScreenMapperImpl : RegistrationScreenMapper {
  override fun invoke(params: Params): RegistrationVM.ScreenData =
    when (params.state) {
      is RegistrationVM.State -> RegistrationVM.ScreenData(
        yes = "",
        onBackClick = params.onBackClick,
      )
    }

}