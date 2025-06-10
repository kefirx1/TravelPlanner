package pl.bla.dev.travelplanner.converter

import com.google.gson.Gson
import pl.bla.dev.common.core.converters.JsonSerializer
import java.lang.reflect.Type

internal class GsonSerializer(
  private val gson: Gson,
): JsonSerializer {
  override fun <T> serialize(data: T): String =
    gson.toJson(data)

  override fun <T> deserialize(serializedData: String?, type: Type): T? =
    gson.fromJson<T>(serializedData, type) as T?

}