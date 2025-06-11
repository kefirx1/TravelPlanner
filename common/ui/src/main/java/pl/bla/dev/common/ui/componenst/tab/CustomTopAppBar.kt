package pl.bla.dev.common.ui.componenst.tab

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import pl.bla.dev.common.ui.componenst.text.CustomText
import pl.bla.dev.common.ui.theming.AppColors

sealed class TopAppBarData(
  open val title: String?,
  val navigationIconId: ImageVector? = null,
  open val onNavigationIconClick: () -> Unit = {},
  val actionIconId: ImageVector? = null,
  open val onActionIconClick: () -> Unit = {},
) {
  data class BackAndTitle(
    override val title: String,
    override val onNavigationIconClick: () -> Unit,
  ): TopAppBarData(
    title = title,
    navigationIconId = Icons.AutoMirrored.Filled.ArrowBack,
    onNavigationIconClick = onNavigationIconClick,
  )

  data class BackAndTitleAction(
    override val title: String,
    override val onNavigationIconClick: () -> Unit,
    override val onActionIconClick: () -> Unit,
  ): TopAppBarData(
    title = title,
    navigationIconId = Icons.AutoMirrored.Filled.ArrowBack,
    onNavigationIconClick = onNavigationIconClick,
    onActionIconClick = onActionIconClick,
    actionIconId = Icons.Default.Close
  )

  data class BackAndAction(
    override val onNavigationIconClick: () -> Unit,
    override val onActionIconClick: () -> Unit,
  ): TopAppBarData(
    title = null,
    navigationIconId = Icons.AutoMirrored.Filled.ArrowBack,
    onNavigationIconClick = onNavigationIconClick,
    onActionIconClick = onActionIconClick,
    actionIconId = Icons.Default.Close
  )

  data class Back(
    override val onNavigationIconClick: () -> Unit,
  ): TopAppBarData(
    title = null,
    navigationIconId = Icons.AutoMirrored.Filled.ArrowBack,
    onNavigationIconClick = onNavigationIconClick,
  )

  data class Title(
    override val title: String,
  ): TopAppBarData(
    title = title,
  )
}

private const val DEBOUNCE_DELAY = 500L

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
  topAppBarData: TopAppBarData,
) {
  var lastClick by remember { mutableLongStateOf(0L) }

  TopAppBar(
    navigationIcon = {
      if (topAppBarData.navigationIconId != null) {
        IconButton(
          onClick = {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClick > DEBOUNCE_DELAY) {
              lastClick = currentTime
              topAppBarData.onNavigationIconClick()
            }
          },
        ) {
          Icon(
            imageVector = topAppBarData.navigationIconId,
            contentDescription = "TAB navigation icon",
          )
        }
      }
    },
    title = {
      topAppBarData.title?.let { title ->
        Spacer(modifier = Modifier.width(40.dp))

        CustomText(
          text = title,
          style = MaterialTheme.typography.headlineMedium,
        )
      }
    },
    actions = {
      if (topAppBarData.actionIconId != null) {
        IconButton(
          onClick = {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClick > DEBOUNCE_DELAY) {
              lastClick = currentTime
              topAppBarData.onActionIconClick()
            }
          },
        ) {
          Icon(
            imageVector = topAppBarData.actionIconId,
            contentDescription = "TAB action icon",
          )
        }
      }
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = AppColors.grey,
      titleContentColor = AppColors.white,
      actionIconContentColor = AppColors.white,
      navigationIconContentColor = AppColors.white,
      scrolledContainerColor = AppColors.grey,
    ),
  )
}

private class CustomTopAppBarProvider : PreviewParameterProvider<TopAppBarData> {
  override val values: Sequence<TopAppBarData> = sequenceOf(
    TopAppBarData.BackAndTitle(
      title = "Title",
      onNavigationIconClick = {},
    ),
    TopAppBarData.Back(
      onNavigationIconClick = {},
    ),
  )
}

@Preview(name = "CustomTopAppBar preview")
@Composable
private fun CustomTopAppBarPreview(
  @PreviewParameter(CustomTopAppBarProvider::class) topAppBarData: TopAppBarData,
) {
  CustomTopAppBar(topAppBarData = topAppBarData)
}