package pl.bla.dev.common.storage.room

import androidx.room.RoomDatabase
import javax.crypto.SecretKey

interface DatabaseProvider {
  fun <T : RoomDatabase> buildDatabase(databaseName: String, databaseClass: Class<T>, masterKey: SecretKey): T
}

