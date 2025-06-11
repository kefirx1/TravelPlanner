package pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.ui.componenst.button.SmallButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData

interface NewTravelVehicleDialogMapper : Mapper<NewTravelVehicleDialogMapper.Params, DialogData> {
  data class Params(
    val type: DialogType,
    val onCloseClick: () -> Unit,
  )

  sealed interface DialogType {
    data object Close : DialogType
  }
}

class NewTravelVehicleDialogMapperImpl : NewTravelVehicleDialogMapper {
  override fun invoke(params: NewTravelVehicleDialogMapper.Params): DialogData =
    when (params.type) {
      NewTravelVehicleDialogMapper.DialogType.Close -> DialogData(
        title = "Czy chcesz zamknąć proces?",
        content = "Dane nowej podróży zostną usunięte i nie będzie można kontynuować tego procesu w przyszłości",
        onDismiss = {},
        onPrimaryButtonData = SmallButtonData.Tertiary(
          text = "Zamknij proces",
          onClick = params.onCloseClick,
        ),
        onSecondaryButtonData = SmallButtonData.Tertiary(
          text = "Zostań",
          onClick = {},
        ),
      )
    }

}