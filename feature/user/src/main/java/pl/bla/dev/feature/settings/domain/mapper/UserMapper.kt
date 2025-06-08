package pl.bla.dev.feature.settings.domain.mapper

import pl.bla.dev.feature.settings.contract.domain.model.UserSettings
import pl.bla.dev.feature.settings.data.model.UserSettingsDto

object UserMapper {

  fun UserSettingsDto.toDomain(): UserSettings =
    UserSettings(
      userName = userName,
    )

  fun UserSettings.toDto(): UserSettingsDto =
    UserSettingsDto(
      userName = userName,
    )

}