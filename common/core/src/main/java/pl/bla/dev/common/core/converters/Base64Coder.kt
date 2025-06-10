package pl.bla.dev.common.core.converters

interface Base64Coder {
  fun decode(data: String?): ByteArray
  fun encode(data: ByteArray?): String
}