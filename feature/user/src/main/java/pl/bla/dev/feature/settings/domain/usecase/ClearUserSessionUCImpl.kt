package pl.bla.dev.feature.settings.domain.usecase

import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.security.MasterKeyProvider
import pl.bla.dev.feature.settings.contract.domain.usecase.ClearUserSessionUC

class ClearUserSessionUCImpl(
  private val masterKeyProvider: MasterKeyProvider,
) : ClearUserSessionUC {
  override suspend fun invoke(param: UseCase.Params.Empty) {
    masterKeyProvider.clearCachedKey()
  }

}