package pl.bla.dev.be.backendservice.domain.mapper

import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContent
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentItem
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContentSection
import pl.bla.dev.be.backendservice.data.model.OnboardingContentDto
import pl.bla.dev.be.backendservice.data.model.OnboardingContentItemDto
import pl.bla.dev.be.backendservice.data.model.OnboardingContentSectionDto

object Mapper {
  fun OnboardingContentDto.toDomain() = OnboardingContent(
    content = content.map { it.toDomain() }
  )

  fun OnboardingContentSectionDto.toDomain() = OnboardingContentSection(
    sectionId = sectionId,
    title = title,
    content = content.map { it.toDomain() }
  )

  fun OnboardingContentItemDto.toDomain() = OnboardingContentItem(
    label = label,
    valueId = valueId,
  )
}