package pl.bla.dev.common.ui.componenst.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import pl.bla.dev.common.ui.componenst.button.SmallButton
import pl.bla.dev.common.ui.componenst.button.SmallButtonData
import pl.bla.dev.common.ui.componenst.card.BaseCard
import pl.bla.dev.common.ui.componenst.input.ValidationState.Companion.isValid
import pl.bla.dev.common.ui.componenst.text.CustomText
import pl.bla.dev.common.ui.theming.AppColors

sealed interface ValidationState {
  data object UnVerified : ValidationState
  data object Valid : ValidationState
  data class Invalid(
    val message: String,
  ) : ValidationState

  companion object {
    fun ValidationState.isValid() = when (this) {
      UnVerified -> true
      Valid -> true
      is Invalid -> false
    }
  }
}

sealed interface TextFieldType {
  data object Default : TextFieldType
  data object Password : TextFieldType
  data object Number : TextFieldType
}

data class TextFieldData(
  val onValueChanged: (String) -> Unit = {},
  val onFocusChanged: (Boolean) -> Unit = {},
  val hint: String = "",
  val label: String? = null,
  val initialText: String = "",
  val linkTextButton: SmallButtonData.Tertiary? = null,
  val validationState: ValidationState = ValidationState.UnVerified,
  val textFieldType: TextFieldType = TextFieldType.Default,
)

@Composable
fun TextField(
  modifier: Modifier = Modifier,
  textFieldData: TextFieldData
) {
  var textValue by remember { mutableStateOf(TextFieldValue(text = textFieldData.initialText)) }
  var hasFocus by remember { mutableStateOf(false) }

  BaseCard {
    Column(
      modifier = modifier.padding(
        vertical = 10.dp,
      )
    ) {
      Column(
        modifier = Modifier.padding(
          horizontal = 10.dp,
        )
      ) {
        textFieldData.label?.let { label ->
          CustomText(
            modifier = Modifier.padding(start = 5.dp),
            text = label,
            style = MaterialTheme.typography.labelMedium,
          )
          Spacer(modifier = Modifier.height(5.dp))
        }

        TextField(
          modifier = Modifier
            .onFocusChanged { focusState ->
              if (focusState.isFocused) {
                hasFocus = true
              }

              if (hasFocus) {
                textFieldData.onFocusChanged(focusState.isFocused)
              }
            },
          placeholder = {
            CustomText(
              text = textFieldData.hint,
              color = AppColors.white.copy(alpha = 0.5f),
            )
          },
          value = textValue,
          onValueChange = { text ->
            textValue = text
            textFieldData.onValueChanged(text.text)
          },
          visualTransformation = when (textFieldData.textFieldType) {
            TextFieldType.Default -> VisualTransformation.None
            TextFieldType.Password -> PasswordVisualTransformation()
            TextFieldType.Number -> VisualTransformation.None
          },
          keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = when (textFieldData.textFieldType) {
              TextFieldType.Default -> KeyboardType.Text
              TextFieldType.Password -> KeyboardType.Password
              TextFieldType.Number -> KeyboardType.Number
            }
          ),
          singleLine = true,
          colors = TextFieldDefaults.colors().copy(
            errorSupportingTextColor = AppColors.white,
            errorTextColor = AppColors.white,
            focusedTextColor = AppColors.white,
            disabledTextColor = AppColors.white,
            unfocusedTextColor = AppColors.white,
            errorContainerColor = AppColors.grey,
            focusedContainerColor = AppColors.grey,
            disabledContainerColor = AppColors.grey,
            unfocusedContainerColor = AppColors.grey,
          ),
          isError = !textFieldData.validationState.isValid()
        )
        when (textFieldData.validationState) {
          ValidationState.UnVerified -> {}
          ValidationState.Valid -> {}
          is ValidationState.Invalid -> {
            Spacer(modifier = Modifier.height(5.dp))

            CustomText(
              modifier = Modifier.padding(start = 5.dp),
              text = textFieldData.validationState.message,
              style = MaterialTheme.typography.labelSmall,
              color = AppColors.invalid,
            )
          }
        }
      }

      textFieldData.linkTextButton?.let { button ->
        Spacer(modifier = Modifier.height(5.dp))

        SmallButton(buttonData = button)
      }
    }
  }
}

private class TextFieldProvider : PreviewParameterProvider<TextFieldData> {
  override val values: Sequence<TextFieldData> = sequenceOf(
    TextFieldData(
      label = "Labelka ładna",
      hint = "hint",
    ),
    TextFieldData(
      hint = "hint",
      initialText = "text input",
      validationState = ValidationState.Invalid(message = "Nieprawidłowe dane"),
      linkTextButton = SmallButtonData.Tertiary(
        text = "Zapomniane hasło?",
        onClick = {},
      )
    )
  )
}

@Preview(name = "TextField preview")
@Composable
fun TextFieldPreview(
  @PreviewParameter(TextFieldProvider::class) textFieldData: TextFieldData,
) {
  TextField(textFieldData = textFieldData)
}