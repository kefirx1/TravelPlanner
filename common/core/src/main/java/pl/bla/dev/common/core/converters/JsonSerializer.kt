package pl.bla.dev.common.core.converters

import java.lang.reflect.Type

interface JsonSerializer {
  fun <T> serialize(data: T): String
  fun <T> deserialize(serializedData: String?, type: Type): T?
}