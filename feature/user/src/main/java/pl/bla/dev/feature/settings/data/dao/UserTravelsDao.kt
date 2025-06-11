package pl.bla.dev.feature.settings.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.bla.dev.feature.settings.data.model.UserTravelsDto

@Dao
interface UserTravelsDao {

  @Query("SELECT * FROM usertravelsdto WHERE user_id = :userId")
  suspend fun getUserTravels(userId: Int): List<UserTravelsDto>

  @Query("SELECT * FROM usertravelsdto WHERE user_id = :userId AND uid = :travelId")
  suspend fun getUserTravelById(userId: Int, travelId: Int): UserTravelsDto?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addTravel(travel: UserTravelsDto)
}