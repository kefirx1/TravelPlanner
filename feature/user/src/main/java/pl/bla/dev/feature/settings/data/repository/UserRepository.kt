package pl.bla.dev.feature.settings.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.bla.dev.be.backendservice.contract.domain.model.NewTravelConfig
import pl.bla.dev.common.security.MasterKeyProvider
import pl.bla.dev.common.storage.room.DatabaseProvider
import pl.bla.dev.feature.settings.contract.domain.model.UserInfo
import pl.bla.dev.feature.settings.contract.domain.model.UserOnboardingPreferences
import pl.bla.dev.feature.settings.contract.domain.model.UserSettings
import pl.bla.dev.feature.settings.contract.domain.model.UserTravels
import pl.bla.dev.feature.settings.data.model.LocalDateTimeConverter
import pl.bla.dev.feature.settings.data.model.OnboardingPreferencesConverter
import pl.bla.dev.feature.settings.data.model.UserInfoDto
import pl.bla.dev.feature.settings.data.model.UserTravelsDto
import pl.bla.dev.feature.settings.data.source.NewTravelConfigDataStore
import pl.bla.dev.feature.settings.data.source.UserDatabase
import pl.bla.dev.feature.settings.data.source.UserSettingsDataStore
import pl.bla.dev.feature.settings.domain.mapper.UserMapper.toDomain
import pl.bla.dev.feature.settings.domain.mapper.UserMapper.toDto
import java.time.LocalDateTime

interface UserRepository {
  suspend fun registerNewUser(
    firstName: String,
    email: String,
    onboardingPreferences: UserOnboardingPreferences,
  )
  suspend fun getUserInfo(): UserInfo?

  suspend fun saveNewUserSettings(userSettings: UserSettings)
  suspend fun getUserSettings(): UserSettings?

  suspend fun getNewTravelConfig(): NewTravelConfig?
  suspend fun saveNewTravelConfig(newTravelConfig: NewTravelConfig)

  suspend fun getUserTravels(): List<UserTravels>
  suspend fun getUserTravel(travelId: Int): UserTravels?
  suspend fun saveUserTravel(
    originCountryId: Int,
    destinationCountryId: Int,
    originCityId: Int,
    destinationCityId: Int,
    originVehicleId: Int,
    destinationVehicleId: Int,
    startDate: LocalDateTime,
    endDate: LocalDateTime,
  ): Int
}

internal class UserRepositoryImpl(
  val userSettingsDataStore: UserSettingsDataStore,
  val newTravelConfigDataStore: NewTravelConfigDataStore,
  val databaseProvider: DatabaseProvider,
  masterKeyProvider: MasterKeyProvider,
  onboardingPreferencesConverter: OnboardingPreferencesConverter,
  localDateTimeConverter: LocalDateTimeConverter,
) : UserRepository {

  private val userDatabase: UserDatabase by lazy {
    databaseProvider.buildDatabase(
      databaseName = UserDatabase.USER_DATABASE_NAME,
      databaseClass = UserDatabase::class.java,
      masterKey = masterKeyProvider.getMasterKey() ?: throw NullPointerException("Master key cannot be null"),
      typeConverters = listOf(
        onboardingPreferencesConverter,
        localDateTimeConverter,
      )
    )
  }

  override suspend fun saveNewUserSettings(userSettings: UserSettings) {
    userSettingsDataStore.save(newSettings = userSettings)
  }

  override suspend fun getUserSettings(): UserSettings? =
    userSettingsDataStore.load()


  override suspend fun registerNewUser(
    firstName: String,
    email: String,
    onboardingPreferences: UserOnboardingPreferences,
  ) = withContext(Dispatchers.IO) {
    userDatabase.userInfoDao().addUser(
      user = UserInfoDto(
        firstName = firstName,
        email = email,
        onboardingPreferences = onboardingPreferences.toDto(),
      )
    )
  }

  override suspend fun getUserInfo(): UserInfo? = withContext(Dispatchers.IO) {
    userDatabase.userInfoDao().getUser()?.toDomain()
  }

  override suspend fun getNewTravelConfig(): NewTravelConfig? =
    newTravelConfigDataStore.load()

  override suspend fun saveNewTravelConfig(newTravelConfig: NewTravelConfig) {
    newTravelConfigDataStore.save(newConfig = newTravelConfig)
  }

  override suspend fun getUserTravel(travelId: Int): UserTravels? = withContext(Dispatchers.IO) {
    val userId = userDatabase.userInfoDao().getUser()?.uid ?: return@withContext null   //TODO error handling

    userDatabase.userTravelsDao().getUserTravelById(
      userId = userId,
      travelId = travelId,
    )?.toDomain()
  }

  override suspend fun getUserTravels(): List<UserTravels> = withContext(Dispatchers.IO) {
    val userId = userDatabase.userInfoDao().getUser()?.uid ?: return@withContext emptyList()   //TODO error handling

    userDatabase.userTravelsDao().getUserTravels(userId = userId).map { it.toDomain() }
  }

  override suspend fun saveUserTravel(
    originCountryId: Int,
    destinationCountryId: Int,
    originCityId: Int,
    destinationCityId: Int,
    originVehicleId: Int,
    destinationVehicleId: Int,
    startDate: LocalDateTime,
    endDate: LocalDateTime,
  ): Int = withContext(Dispatchers.IO) {
    val userId = userDatabase.userInfoDao().getUser()?.uid ?: return@withContext -1  //TODO error handling

    userDatabase.userTravelsDao().addTravel(
      travel = UserTravelsDto(
        userId = userId,
        originCountryId = originCountryId,
        destinationCountryId = destinationCountryId,
        originCityId = originCityId,
        destinationCityId = destinationCityId,
        cancelled = false,
        startDate = startDate,
        endDate = endDate,
        originVehicleId = originVehicleId,
        destinationVehicleId = destinationVehicleId,
      )
    ).toInt()
  }
}