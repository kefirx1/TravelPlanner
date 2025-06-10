package pl.bla.dev.travelplanner.converter

import android.util.Base64
import pl.bla.dev.common.core.converters.Base64Coder

internal class Base64CoderImpl : Base64Coder {
  override fun decode(data: String?): ByteArray =
    Base64.decode(data, Base64.NO_WRAP)

  override fun encode(data: ByteArray?): String =
    Base64.encodeToString(data, Base64.NO_WRAP)
}