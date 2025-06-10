package pl.bla.dev.feature.login.presentation.screen.login.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.ui.componenst.button.SmallButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData

interface LoginScreenDialogMapper : Mapper<LoginScreenDialogMapper.Params, DialogData> {
  data class Params(
    val dialogType: LoginDialogType,
    val onResetClick: () -> Unit,
  ): UseCase.Params

  sealed interface LoginDialogType {
    data object ForgotPassword : LoginDialogType
  }
}

internal class LoginScreenDialogMapperImpl : LoginScreenDialogMapper {
  override fun invoke(params: LoginScreenDialogMapper.Params): DialogData =
    when (params.dialogType) {
      LoginScreenDialogMapper.LoginDialogType.ForgotPassword -> DialogData(
        title = "Nie pamiętasz hasła?",
        content = "Kliknij 'Wyczyść', aby zresetować dane aplikacji i rozpocząć od nowa.\n\nUwaga nie można tego cofnąć!",
        onDismiss = {},
        onPrimaryButtonData = SmallButtonData.Tertiary(
          text = "Wyczyść",
          onClick = params.onResetClick,
        ),
        onSecondaryButtonData = SmallButtonData.Tertiary(
          text = "Anuluj",
          onClick = {},
        ),
      )
    }

}