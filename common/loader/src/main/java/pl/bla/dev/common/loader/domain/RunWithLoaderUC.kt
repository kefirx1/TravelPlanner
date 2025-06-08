package pl.bla.dev.common.loader.domain

import pl.bla.dev.common.loader.LoaderManager

interface RunWithLoaderUC {
  suspend operator fun <RESULT> invoke(action: suspend () -> RESULT): RESULT
}

internal class RunWithLoaderUCImpl(
  private val loaderManager: LoaderManager,
) : RunWithLoaderUC {
  override suspend operator fun <RESULT> invoke(action: suspend () -> RESULT): RESULT {
    loaderManager.showLoader()
    val result = action()
    loaderManager.hideLoader()
    return result
  }

}