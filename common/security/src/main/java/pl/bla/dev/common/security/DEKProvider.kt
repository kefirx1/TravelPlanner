package pl.bla.dev.common.security

import javax.crypto.SecretKey

interface DEKProvider {
  fun getDEK(): SecretKey
}