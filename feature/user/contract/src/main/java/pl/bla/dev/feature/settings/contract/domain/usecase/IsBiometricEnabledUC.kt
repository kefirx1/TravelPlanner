package pl.bla.dev.feature.settings.contract.domain.usecase

import pl.bla.dev.common.core.usecase.EitherUseCase
import pl.bla.dev.common.core.usecase.UseCase

interface IsBiometricEnabledUC : EitherUseCase<UseCase.Params.Empty, Boolean>