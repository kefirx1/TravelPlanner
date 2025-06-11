package pl.bla.dev.feature.login.domain.usecase

import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.feature.settings.contract.domain.usecase.FetchNewTravelConfigUC

interface AfterLoginActionUC : UseCase<UseCase.Params.Empty, Unit>

internal class AfterLoginActionUCImpl(
  private val fetchNewTravelConfigUC: FetchNewTravelConfigUC,
): AfterLoginActionUC {

  override suspend fun invoke(param: UseCase.Params.Empty) {
    fetchNewTravelConfigUC(UseCase.Params.Empty)
  }
}