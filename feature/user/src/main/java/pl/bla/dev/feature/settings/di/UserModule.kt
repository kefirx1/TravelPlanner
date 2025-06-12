package pl.bla.dev.feature.settings.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bla.dev.be.backendservice.contract.domain.usecase.GetServiceNewTravelConfigUC
import pl.bla.dev.common.core.converters.Base64Coder
import pl.bla.dev.common.core.converters.JsonSerializer
import pl.bla.dev.common.security.CryptoManager
import pl.bla.dev.common.security.MasterKeyProvider
import pl.bla.dev.common.security.SecretKeyProvider
import pl.bla.dev.common.security.domain.GenerateSaltUC
import pl.bla.dev.common.storage.datastore.DataStoreProvider
import pl.bla.dev.common.storage.room.DatabaseProvider
import pl.bla.dev.feature.settings.contract.domain.usecase.DecryptUserDEKAndInjectCacheUC
import pl.bla.dev.feature.settings.contract.domain.usecase.FetchNewTravelConfigUC
import pl.bla.dev.feature.settings.contract.domain.usecase.GetFullTravelDataUC
import pl.bla.dev.feature.settings.contract.domain.usecase.GetSavedNewTravelConfigUC
import pl.bla.dev.feature.settings.contract.domain.usecase.GetSavedUserNameUC
import pl.bla.dev.feature.settings.contract.domain.usecase.GetUserTravelsShortDataUC
import pl.bla.dev.feature.settings.contract.domain.usecase.RegisterNewUserUC
import pl.bla.dev.feature.settings.contract.domain.usecase.SaveNewTravelUC
import pl.bla.dev.feature.settings.data.model.LocalDateTimeConverter
import pl.bla.dev.feature.settings.data.model.OnboardingPreferencesConverter
import pl.bla.dev.feature.settings.data.repository.UserRepository
import pl.bla.dev.feature.settings.data.repository.UserRepositoryImpl
import pl.bla.dev.feature.settings.data.source.NewTravelConfigDataStore
import pl.bla.dev.feature.settings.data.source.NewTravelConfigPreferencesDataStore
import pl.bla.dev.feature.settings.data.source.UserSettingsDataStore
import pl.bla.dev.feature.settings.data.source.UserSettingsPreferencesDataStore
import pl.bla.dev.feature.settings.domain.usecase.DecryptUserDEKAndInjectCacheUCImpl
import pl.bla.dev.feature.settings.domain.usecase.FetchNewTravelConfigUCImpl
import pl.bla.dev.feature.settings.domain.usecase.GetCountryTravelConfigByIdUC
import pl.bla.dev.feature.settings.domain.usecase.GetCountryTravelConfigByIdUCImpl
import pl.bla.dev.feature.settings.domain.usecase.GetFullTravelDataUCImpl
import pl.bla.dev.feature.settings.domain.usecase.GetSavedNewTravelConfigUCImpl
import pl.bla.dev.feature.settings.domain.usecase.GetSavedUserNameUCImpl
import pl.bla.dev.feature.settings.domain.usecase.GetUserTravelsShortDataUCImpl
import pl.bla.dev.feature.settings.domain.usecase.RegisterNewUserUCImpl
import pl.bla.dev.feature.settings.domain.usecase.SaveNewTravelUCImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

  @Singleton
  @Provides
  fun provideOnboardingPreferencesConverter(
    jsonSerializer: JsonSerializer,
  ) = OnboardingPreferencesConverter(
    jsonSerializer = jsonSerializer,
  )

  @Provides
  @Singleton
  fun provideLocalDateTimeConverter(
    jsonSerializer: JsonSerializer,
  ) = LocalDateTimeConverter(
    jsonSerializer = jsonSerializer,
  )

  @Singleton
  @Provides
  fun provideSettingsDataStore(
    dataStoreProvider: DataStoreProvider,
  ): UserSettingsDataStore = UserSettingsPreferencesDataStore(
    dataStoreProvider = dataStoreProvider,
  )

  @Provides
  @Singleton
  fun provideNewTravelConfigDataStore(
    dataStoreProvider: DataStoreProvider,
  ): NewTravelConfigDataStore = NewTravelConfigPreferencesDataStore(
    dataStoreProvider = dataStoreProvider,
  )

  @Singleton
  @Provides
  fun provideUserRepository(
    userSettingsDataStore: UserSettingsDataStore,
    masterKeyProvider: MasterKeyProvider,
    databaseProvider: DatabaseProvider,
    onboardingPreferencesConverter: OnboardingPreferencesConverter,
    newTravelConfigDataStore: NewTravelConfigDataStore,
    localDateTimeConverter: LocalDateTimeConverter,
  ): UserRepository = UserRepositoryImpl(
    userSettingsDataStore = userSettingsDataStore,
    masterKeyProvider = masterKeyProvider,
    databaseProvider = databaseProvider,
    onboardingPreferencesConverter = onboardingPreferencesConverter,
    newTravelConfigDataStore = newTravelConfigDataStore,
    localDateTimeConverter = localDateTimeConverter,
  )

  @Provides
  fun provideRegisterNewUserUC(
    userRepository: UserRepository,
    secretKeyProvider: SecretKeyProvider,
    generateSaltUC: GenerateSaltUC,
    cryptoManager: CryptoManager,
    masterKeyProvider: MasterKeyProvider,
    base64Coder: Base64Coder,
  ): RegisterNewUserUC = RegisterNewUserUCImpl(
    userRepository = userRepository,
    secretKeyProvider = secretKeyProvider,
    generateSaltUC = generateSaltUC,
    cryptoManager = cryptoManager,
    masterKeyProvider = masterKeyProvider,
    base64Coder = base64Coder,
  )

  @Provides
  fun provideDecryptUserDEKUC(
    userRepository: UserRepository,
    cryptoManager: CryptoManager,
    secretKeyProvider: SecretKeyProvider,
    masterKeyProvider: MasterKeyProvider,
    base64Coder: Base64Coder,
  ): DecryptUserDEKAndInjectCacheUC = DecryptUserDEKAndInjectCacheUCImpl(
    userRepository = userRepository,
    cryptoManager = cryptoManager,
    secretKeyProvider = secretKeyProvider,
    masterKeyProvider = masterKeyProvider,
    base64Coder = base64Coder,
  )

  @Provides
  fun provideIsAppRegisteredUC(
    userRepository: UserRepository,
  ): GetSavedUserNameUC = GetSavedUserNameUCImpl(
    userRepository = userRepository,
  )

  @Provides
  fun provideGetUserTravelsShortDataUC(
    userRepository: UserRepository,
  ): GetUserTravelsShortDataUC = GetUserTravelsShortDataUCImpl(
    userRepository = userRepository,
  )

  @Provides
  fun provideFetchNewTravelConfigUC(
    getServiceNewTravelConfigUC: GetServiceNewTravelConfigUC,
    userRepository: UserRepository,
  ): FetchNewTravelConfigUC = FetchNewTravelConfigUCImpl(
    getServiceNewTravelConfigUC = getServiceNewTravelConfigUC,
    userRepository = userRepository,
  )

  @Provides
  fun provideGetSavedNewTravelConfigUC(
    userRepository: UserRepository,
  ): GetSavedNewTravelConfigUC = GetSavedNewTravelConfigUCImpl(
    userRepository = userRepository,
  )

  @Provides
  fun provideSaveNewTravelUC(
    userRepository: UserRepository,
    getCountryTravelConfigByIdUC: GetCountryTravelConfigByIdUC,
  ): SaveNewTravelUC = SaveNewTravelUCImpl(
    userRepository = userRepository,
    getCountryTravelConfigByIdUC = getCountryTravelConfigByIdUC,
  )

  @Provides
  fun provideGetFullTravelDataUC(
    userRepository: UserRepository,
  ): GetFullTravelDataUC = GetFullTravelDataUCImpl(
    userRepository = userRepository,
  )

  @Provides
  fun provideGetCountryTravelConfigByIdUC(
    getSavedNewTravelConfigUC: GetSavedNewTravelConfigUC,
  ): GetCountryTravelConfigByIdUC = GetCountryTravelConfigByIdUCImpl(
    getSavedNewTravelConfigUC = getSavedNewTravelConfigUC,
  )
}