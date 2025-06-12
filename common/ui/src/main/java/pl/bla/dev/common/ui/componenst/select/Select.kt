package pl.bla.dev.common.ui.componenst.select

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import pl.bla.dev.common.ui.theming.AppColors

data class SelectItemData(
  val id: Int,
  val label: String,
)

data class SelectData(
  val content: List<SelectItemData>,
  val selectedOption: Int,
  val onSelect: (Int) -> Unit,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Select(data: SelectData) {
  var isExpanded by remember { mutableStateOf(false) }

  ExposedDropdownMenuBox(
    modifier = Modifier.fillMaxWidth(),
    expanded = isExpanded,
    onExpandedChange = { isExpanded = it },
  ) {
    OutlinedTextField(
      modifier = Modifier
        .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
        .fillMaxWidth(),
      readOnly = true,
      value = data.content.find { it.id == data.selectedOption }?.label ?: "",
      onValueChange = {},
      maxLines = 1,
      trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
      colors = ExposedDropdownMenuDefaults.textFieldColors(
        focusedTextColor = AppColors.white,
        unfocusedTextColor = AppColors.white,
        focusedContainerColor = AppColors.grey,
        unfocusedContainerColor = AppColors.grey,
        focusedIndicatorColor = AppColors.blue2,
        unfocusedIndicatorColor = AppColors.blue2,
        focusedTrailingIconColor = AppColors.blue2,
        unfocusedTrailingIconColor = AppColors.blue2,
      ),
    )

    ExposedDropdownMenu(
      modifier = Modifier,
      expanded = isExpanded,
      onDismissRequest = { isExpanded = false },
    ) {
      data.content.forEachIndexed { index, option ->
        DropdownMenuItem(
          text = { Text(option.label) },
          onClick = {
            data.onSelect(option.id)
            isExpanded = false
          },
          contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
        )
      }
    }
  }
}

private class SelectProvider : PreviewParameterProvider<SelectData> {
  override val values: Sequence<SelectData> = sequenceOf(
    SelectData(
      content = listOf(
        SelectItemData(label = "Option 1", id = 1),
        SelectItemData(label = "Option 2", id = 2),
        SelectItemData(label = "Option 3", id = 3),
      ),
      selectedOption = 1,
      onSelect = {},
    )
  )
}

@Preview(name = "Select preview")
@Composable
private fun SelectPreview(
  @PreviewParameter(SelectProvider::class) data: SelectData,
) {
  Select(data)
}