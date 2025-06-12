package pl.bla.dev.feature.travel.presentation.screen.details.mapper

import pl.bla.dev.common.core.usecase.Mapper
import pl.bla.dev.common.ui.componenst.button.SmallButtonData
import pl.bla.dev.common.ui.componenst.dialog.DialogData

interface TravelDetailsDialogMapper : Mapper<TravelDetailsDialogMapper.Params, DialogData> {
  data class Params(
    val type: DialogType,
    val onRemoveTravelClick: () -> Unit,
    val onCancelTravelClick: () -> Unit,
    val onRestoreTravelClick: () -> Unit,
  )

  sealed interface DialogType {
    data object Cancel : DialogType
    data object Restore : DialogType
    data object Remove : DialogType
  }
}

class TravelDetailsDialogMapperImpl : TravelDetailsDialogMapper {
  override fun invoke(params: TravelDetailsDialogMapper.Params): DialogData =
    when (params.type) {
      TravelDetailsDialogMapper.DialogType.Cancel -> DialogData(
        title = "Czy chcesz odwołać wycieczkę?",
        content = "Podróż zostanie anulowana i zmieni swój status na liście, a przypominienia zostaną usunięte",
        onDismiss = {},
        onPrimaryButtonData = SmallButtonData.Tertiary(
          text = "Odwołaj",
          onClick = params.onCancelTravelClick,
        ),
        onSecondaryButtonData = SmallButtonData.Tertiary(
          text = "Wróć",
          onClick = {},
        ),
      )
      TravelDetailsDialogMapper.DialogType.Restore -> DialogData(
        title = "Czy chcesz przywrócić wycieczkę?",
        content = "Podróż zostanie przywrócona i zmieni swój status na liście, a przypominienia zostaną dodane",
        onDismiss = {},
        onPrimaryButtonData = SmallButtonData.Tertiary(
          text = "Przywróć",
          onClick = params.onRestoreTravelClick,
        ),
        onSecondaryButtonData = SmallButtonData.Tertiary(
          text = "Wróć",
          onClick = {},
        ),
      )
      TravelDetailsDialogMapper.DialogType.Remove -> DialogData(
        title = "Czy chcesz usunąć wycieczkę?",
        content = "Podróż zostanie całkowicie usunięta i nie będzie możliwości jej przywrócenia",
        onDismiss = {},
        onPrimaryButtonData = SmallButtonData.Tertiary(
          text = "Usuń",
          onClick = params.onRemoveTravelClick,
        ),
        onSecondaryButtonData = SmallButtonData.Tertiary(
          text = "Wróć",
          onClick = {},
        ),
      )
    }

}