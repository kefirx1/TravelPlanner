package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.error.AppError
import pl.bla.dev.common.core.usecase.Either
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.feature.settings.contract.domain.model.LocomotionType
import pl.bla.dev.feature.settings.contract.domain.model.TravelShortData
import pl.bla.dev.feature.settings.contract.domain.model.TravelStatus
import pl.bla.dev.feature.settings.contract.domain.usecase.GetUserTravelsShortDataUC
import pl.bla.dev.feature.settings.data.repository.UserRepository
import java.time.LocalDateTime

class GetUserTravelsShortDataUCImpl(
  private val userRepository: UserRepository,
) : GetUserTravelsShortDataUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Either<AppError, List<TravelShortData>> {
    //TODO get data


    return Either.Right(
      listOf(
        TravelShortData(
          id = "1",
          origin = "Warszawa",
          originCountry = "Polska",
          destination = "Londyn",
          destinationCountry = "Wielka Brytania",
          date = LocalDateTime.now().plusMonths(2),
          travelStatus = TravelStatus.FUTURE,
          locomotionType = LocomotionType.PLANE,
        ),
        TravelShortData(
          id = "2",
          origin = "Warszawa",
          originCountry = "Polska",
          destination = "Londyn",
          destinationCountry = "Wielka Brytania",
          date = LocalDateTime.now().plusMonths(6),
          travelStatus = TravelStatus.FUTURE,
          locomotionType = LocomotionType.PLANE,
        ),
        TravelShortData(
          id = "3",
          origin = "Poznań",
          originCountry = "Polska",
          destination = "Rzym",
          destinationCountry = "Włochy",
          date = LocalDateTime.now().minusMonths(4),
          travelStatus = TravelStatus.PAST,
          locomotionType = LocomotionType.CAR,
        ),
        TravelShortData(
          id = "4",
          origin = "Poznań",
          originCountry = "Polska",
          destination = "Berlin",
          destinationCountry = "Niemcy",
          date = LocalDateTime.now().plusWeeks(3),
          travelStatus = TravelStatus.FUTURE,
          locomotionType = LocomotionType.TRAIN,
        ),
        TravelShortData(
          id = "5",
          origin = "Warszawa",
          originCountry = "Polska",
          destination = "Tokio",
          destinationCountry = "Japonia",
          date = LocalDateTime.now().minusMonths(6),
          travelStatus = TravelStatus.PAST,
          locomotionType = LocomotionType.PLANE,
        ),
        TravelShortData(
          id = "6",
          origin = "Kraków",
          originCountry = "Polska",
          destination = "Gdańsk",
          destinationCountry = "Polska",
          date = LocalDateTime.now().minusDays(2),
          travelStatus = TravelStatus.CURRENT,
          locomotionType = LocomotionType.CAR,
        ),
        TravelShortData(
          id = "7",
          origin = "Wrocław",
          originCountry = "Polska",
          destination = "Praga",
          destinationCountry = "Czechy",
          date = LocalDateTime.now().plusMonths(1),
          travelStatus = TravelStatus.CANCELLED,
          locomotionType = LocomotionType.BUS,
        ),
        TravelShortData(
          id = "8",
          origin = "Katowice",
          originCountry = "Polska",
          destination = "Barcelona",
          destinationCountry = "Hiszpania",
          date = LocalDateTime.now().plusMonths(2),
          travelStatus = TravelStatus.FUTURE,
          locomotionType = LocomotionType.PLANE,
        ),
        TravelShortData(
          id = "9",
          origin = "Poznań",
          originCountry = "Polska",
          destination = "Wiedeń",
          destinationCountry = "Austria",
          date = LocalDateTime.now().minusYears(1),
          travelStatus = TravelStatus.PAST,
          locomotionType = LocomotionType.TRAIN,
        ),
        TravelShortData(
          id = "10",
          origin = "Gdańsk",
          originCountry = "Polska",
          destination = "Zakopane",
          destinationCountry = "Polska",
          date = LocalDateTime.now().plusDays(10),
          travelStatus = TravelStatus.FUTURE,
          locomotionType = LocomotionType.CAR,
        ),
        TravelShortData(
          id = "11",
          origin = "Warszawa",
          originCountry = "Polska",
          destination = "Nowy Jork",
          destinationCountry = "USA",
          date = LocalDateTime.now().minusYears(3),
          travelStatus = TravelStatus.PAST,
          locomotionType = LocomotionType.PLANE,
        ),
        TravelShortData(
          id = "12",
          origin = "Poznań",
          originCountry = "Polska",
          destination = "Rzym",
          destinationCountry = "Włochy",
          date = LocalDateTime.now().minusMonths(4),
          travelStatus = TravelStatus.CANCELLED,
          locomotionType = LocomotionType.PLANE,
        ),
        TravelShortData(
          id = "13",
          origin = "Kraków",
          originCountry = "Polska",
          destination = "Budapeszt",
          destinationCountry = "Węgry",
          date = LocalDateTime.now().plusWeeks(5),
          travelStatus = TravelStatus.FUTURE,
          locomotionType = LocomotionType.BUS,
        ),
      )
    )
  }
}