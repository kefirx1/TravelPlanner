package pl.bla.dev.common.intents.domain.usecase

import pl.bla.dev.common.intents.IntentsManager
import pl.bla.dev.common.usecase.UseCase

interface OpenAppSettingsIntentUC: UseCase<UseCase.Params.Empty, Unit>

internal class OpenAppSettingsIntentUCImpl(
  private val intentsManager: IntentsManager,
) : OpenAppSettingsIntentUC {
  override suspend fun invoke(param: UseCase.Params.Empty) {
    intentsManager.startAppSettingsIntent()
  }
}