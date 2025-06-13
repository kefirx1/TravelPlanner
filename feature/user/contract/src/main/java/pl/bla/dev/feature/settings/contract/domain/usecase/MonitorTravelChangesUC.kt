package pl.bla.dev.feature.settings.contract.domain.usecase

import kotlinx.coroutines.flow.Flow
import pl.bla.dev.common.core.usecase.UseCase

interface MonitorTravelChangesUC : UseCase<UseCase.Params.Empty, Flow<Unit>>