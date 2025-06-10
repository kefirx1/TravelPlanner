package pl.bla.dev.feature.settings.domain.mapper

import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentItem
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentSection
import pl.bla.dev.feature.settings.contract.domain.model.UserSettings
import pl.bla.dev.feature.settings.data.model.UserOnboardingPreferencesItem
import pl.bla.dev.feature.settings.data.model.UserOnboardingPreferencesSection
import pl.bla.dev.feature.settings.data.model.UserSettingsDto

object UserMapper {

  fun UserSettingsDto.toDomain(): UserSettings =
    UserSettings(
      userName = userName,
      salt = salt,
      ivDek = ivDek,
    )

  fun UserSettings.toDto(): UserSettingsDto =
    UserSettingsDto(
      userName = userName,
      salt = salt,
      ivDek = ivDek,
    )

  fun OnboardingContentSection.toDto() = UserOnboardingPreferencesSection(
    content = content.map { it.toDto() },
    sectionId = sectionId,
    title = title,
  )

  fun OnboardingContentItem.toDto() = UserOnboardingPreferencesItem(
    label = label,
    valueId = valueId,
  )


}