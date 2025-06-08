package pl.bla.dev.common.core.loader

import pl.bla.dev.common.core.usecase.UseCase

interface RunWithLoaderUC : UseCase<suspend () -> Unit, Unit>

internal class RunWithLoaderUCImpl(
  private val loaderManager: LoaderManager,
) : RunWithLoaderUC {
  override suspend fun invoke(params: suspend () -> Unit) {
    loaderManager.showLoader()
    params()
    loaderManager.hideLoader()
  }

}