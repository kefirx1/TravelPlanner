package pl.bla.dev.feature.dashboard.presentation.screen.main.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.ui.componenst.button.SmallButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData

interface MainDashboardDialogMapper : Mapper<MainDashboardDialogMapper.Params, DialogData> {
  data class Params(
    val type: DialogType,
    val onLogoutClick: () -> Unit,
  )

  sealed interface DialogType {
    data object Logout : DialogType
  }
}

class MainDashboardDialogMapperImpl : MainDashboardDialogMapper {
  override fun invoke(params: MainDashboardDialogMapper.Params): DialogData =
    when (params.type) {
      MainDashboardDialogMapper.DialogType.Logout -> DialogData(
        title = "Chcesz się wylogować?",
        content = "Kliknij wyloguj, aby wyjść z aplikacji",
        onDismiss = {},
        onPrimaryButtonData = SmallButtonData.Tertiary(
          text = "Wyloguj",
          onClick = params.onLogoutClick,
        ),
        onSecondaryButtonData = SmallButtonData.Tertiary(
          text = "Nie",
          onClick = {},
        ),
      )
    }

}