package pl.bla.dev.feature.dashboard.presentation.screen.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.ui.componenst.button.SmallButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.feature.dashboard.presentation.DashboardDestinations
import pl.bla.dev.feature.dashboard.presentation.screen.DashboardDialogVM

interface DashboardDialogMapper : Mapper<DashboardDialogMapper.Params, DashboardDialogVM.ScreenData> {
  data class Params(
    val dialogType: DashboardDestinations.DashboardDialog.Types,
    val onDismiss: () -> Unit,
    val onLogout: () -> Unit,
  )
}

class DashboardDialogMapperImpl : DashboardDialogMapper {
  override fun invoke(params: DashboardDialogMapper.Params): DashboardDialogVM.ScreenData =
    when (params.dialogType) {
      DashboardDestinations.DashboardDialog.Types.LOGOUT_DIALOG -> DashboardDialogVM.ScreenData(
        dialogData = DialogData(
          title = "Chcesz się wylogować?",
          content = "Kliknij wyloguj, aby wyjść z aplikacji",
          onDismiss = params.onDismiss,
          onPrimaryButtonData = SmallButtonData.Tertiary(
            text = "Wyloguj",
            onClick = params.onLogout,
          ),
          onSecondaryButtonData = SmallButtonData.Tertiary(
            text = "Nie",
            onClick = params.onDismiss,
          ),
        )
      )
    }

}