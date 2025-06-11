package pl.bla.dev.feature.settings.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.bla.dev.feature.settings.data.model.UserInfoDto

@Dao
interface UserInfoDao {

  @Query("SELECT * FROM userinfodto")
  suspend fun getAll(): List<UserInfoDto>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addUser(user: UserInfoDto)

  @Query("SELECT * FROM UserInfoDto LIMIT 1")
  suspend fun getUser(): UserInfoDto?
}