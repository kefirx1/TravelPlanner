package pl.bla.dev.be.backendservice.data.repository

import kotlinx.coroutines.delay
import pl.bla.dev.be.backendservice.contract.domain.model.OnboardingContent
import pl.bla.dev.be.backendservice.data.model.OnboardingContentDto
import pl.bla.dev.be.backendservice.data.model.OnboardingContentItemDto
import pl.bla.dev.be.backendservice.data.model.OnboardingContentSectionDto
import pl.bla.dev.be.backendservice.domain.mapper.Mapper.toDomain
import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either

interface BackendServiceRepository {
  suspend fun getOnboardingContent(): Either<AppError, OnboardingContent>
}

internal class BackendServiceRepositoryImpl: BackendServiceRepository {

  //TODO server value
  override suspend fun getOnboardingContent(): Either<AppError, OnboardingContent> {
    delay(1000)
    return Either.Right(
      value = OnboardingContentDto(
        content = listOf(
          OnboardingContentSectionDto(
            sectionId = 1,
            title = "Co najbardziej lubisz w podróżowaniu?",
            content = listOf(
              OnboardingContentItemDto(
                label = "Zwiedzanie i historia",
                valueId = 100,
              ),
              OnboardingContentItemDto(
                label = "Natura i trekking",
                valueId = 101,
              ),
              OnboardingContentItemDto(
                label = "Plażowanie",
                valueId = 102,
              ),
              OnboardingContentItemDto(
                label = "Życie nocne",
                valueId = 103,
              ),
              OnboardingContentItemDto(
                label = "Odwiedzanie miast",
                valueId = 104,
              ),
            ),
          ),
          OnboardingContentSectionDto(
            sectionId = 2,
            title = "Czym najchętniej wyruszasz w podróż?",
            content = listOf(
              OnboardingContentItemDto(
                label = "Samochód",
                valueId = 200,
              ),
              OnboardingContentItemDto(
                label = "Samolot",
                valueId = 201,
              ),
              OnboardingContentItemDto(
                label = "Pociąg",
                valueId = 202,
              ),
            ),
          ),
          OnboardingContentSectionDto(
            sectionId = 3,
            title = "Jakiej długości podróże preferujesz?",
            content = listOf(
              OnboardingContentItemDto(
                label = "1 dniowe",
                valueId = 300,
              ),
              OnboardingContentItemDto(
                label = "2 do 3 dni",
                valueId = 301,
              ),
              OnboardingContentItemDto(
                label = "4 do 7 dni",
                valueId = 302,
              ),
              OnboardingContentItemDto(
                label = "Ponad tygodniowe",
                valueId = 303,
              ),
            ),
          ),
        )
      ).toDomain()
    )
  }
}