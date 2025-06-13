package pl.bla.dev.feature.settings.domain.usecase

import kotlinx.coroutines.flow.Flow
import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.feature.settings.contract.domain.usecase.MonitorTravelChangesUC
import pl.bla.dev.feature.settings.data.repository.UserRepository

class MonitorTravelChangesUCImpl(
  private val userRepository: UserRepository,
) : MonitorTravelChangesUC {
  override suspend fun invoke(param: UseCase.Params.Empty): Flow<Unit> =
    userRepository.getTravelsChangesMonitor()
}