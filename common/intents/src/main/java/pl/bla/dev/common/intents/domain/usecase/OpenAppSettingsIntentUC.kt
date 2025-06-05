package pl.bla.dev.common.intents.domain.usecase

import pl.bla.dev.common.core.usecase.UseCase
import pl.bla.dev.common.intents.IntentsManager

interface OpenAppSettingsIntentUC: UseCase<UseCase.Params.Empty, Unit>

internal class OpenAppSettingsIntentUCImpl(
  private val intentsManager: IntentsManager,
) : OpenAppSettingsIntentUC {
  override suspend fun invoke(param: UseCase.Params.Empty) {
    intentsManager.startAppSettingsIntent()
  }
}