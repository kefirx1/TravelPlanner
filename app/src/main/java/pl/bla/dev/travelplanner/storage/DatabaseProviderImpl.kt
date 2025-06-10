package pl.bla.dev.travelplanner.storage

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import net.sqlcipher.database.SupportFactory
import pl.bla.dev.common.storage.room.DatabaseProvider
import javax.crypto.SecretKey

internal class DatabaseProviderImpl(
  private val context: Context,
): DatabaseProvider {
  override fun <T : RoomDatabase> buildDatabase(
    databaseName: String,
    databaseClass: Class<T>,
    masterKey: SecretKey,
    typeConverters: List<Any>,
  ): T {
    val factory = SupportFactory(masterKey.encoded)

    return Room.databaseBuilder(
      context.applicationContext,
      databaseClass,
      databaseName,
    ).openHelperFactory(factory)
      .apply {
        typeConverters.forEach { converter ->
          addTypeConverter(converter)
        }
      }
      .build()
  }
}