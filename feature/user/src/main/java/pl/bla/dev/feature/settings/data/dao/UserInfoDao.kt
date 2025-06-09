package pl.bla.dev.feature.settings.data.dao

import androidx.room.Dao
import androidx.room.Query
import pl.bla.dev.feature.settings.data.model.UserInfo

@Dao
interface UserInfoDao {

  @Query("SELECT * FROM userinfo")
  fun getAll(): List<UserInfo>

}