package pl.bla.dev.common.biometric

import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pl.bla.dev.common.activityconnector.ActivityConnector
import javax.crypto.Cipher
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface BiometricPromptConnector : ActivityConnector

interface BiometricPromptManager {
  suspend fun showBiometricPrompt(
    cipher: Cipher,
    title: String,
    description: String,
    negativeButton: String,
  ): BiometricResult
}

class BiometricPromptManagerImpl : BiometricPromptManager, BiometricPromptConnector {
  lateinit var activity: AppCompatActivity

  @OptIn(ExperimentalCoroutinesApi::class)
  override suspend fun showBiometricPrompt(
    cipher: Cipher,
    title: String,
    description: String,
    negativeButton: String,
  ): BiometricResult  = suspendCoroutine { continuation ->
    val manager = BiometricManager.from(activity)
    val authenticators = BIOMETRIC_STRONG

    val promptInfo = PromptInfo.Builder()
      .setTitle(title)
      .setDescription(description)
      .setAllowedAuthenticators(authenticators)
      .setNegativeButtonText(negativeButton)
      .build()

    when (manager.canAuthenticate(authenticators)) {
      BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
        continuation.resume(BiometricResult.HardwareUnavailable)
      }
      BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
        continuation.resume(BiometricResult.FeatureUnavailable)
      }
      BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
        continuation.resume(BiometricResult.AuthenticationNotSet)
      }
      else -> Unit
    }

    val prompt = BiometricPrompt(
      activity,
      object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
          super.onAuthenticationError(errorCode, errString)
          continuation.resume(BiometricResult.AuthenticationError(errString.toString()))
        }

        override fun onAuthenticationFailed() {
          super.onAuthenticationFailed()
          continuation.resume(BiometricResult.AuthenticationFailed)
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
          super.onAuthenticationSucceeded(result)

          if (result.cryptoObject?.cipher != null) {
            continuation.resume(BiometricResult.AuthenticationSuccess(cipher = result.cryptoObject?.cipher!!))
          }
        }
      },
    )


    prompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
  }

  override fun connect(activity: AppCompatActivity) {
    this.activity = activity
  }

}

sealed interface BiometricResult {
  data object HardwareUnavailable: BiometricResult
  data object FeatureUnavailable: BiometricResult
  data class AuthenticationSuccess(
    val cipher: Cipher,
  ) : BiometricResult
  data class AuthenticationError(val error: String): BiometricResult
  data object AuthenticationFailed: BiometricResult
  data object AuthenticationNotSet: BiometricResult
}