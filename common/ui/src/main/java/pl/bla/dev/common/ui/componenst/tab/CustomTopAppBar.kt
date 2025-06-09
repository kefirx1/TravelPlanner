package pl.bla.dev.common.ui.componenst.tab

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import pl.bla.dev.common.ui.R
import pl.bla.dev.common.ui.componenst.text.CustomText
import pl.bla.dev.common.ui.theming.AppColors

sealed class TopAppBarData(
  open val title: String?,
  val navigationIconId: Int? = null,
  open val onNavigationIconClick: () -> Unit = {},
  val actionIconId: Int? = null,
  open val onActionIconClick: () -> Unit = {},
) {
  data class BackAndTitle(
    override val title: String,
    override val onNavigationIconClick: () -> Unit,
  ): TopAppBarData(
    title = title,
    navigationIconId = R.drawable.baseline_arrow_back_24,
    onNavigationIconClick = onNavigationIconClick,
  )

  data class Back(
    override val onNavigationIconClick: () -> Unit,
  ): TopAppBarData(
    title = null,
    navigationIconId = R.drawable.baseline_arrow_back_24,
    onNavigationIconClick = onNavigationIconClick,
  )

  data class Title(
    override val title: String,
  ): TopAppBarData(
    title = title,
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
  topAppBarData: TopAppBarData,
) {
  TopAppBar(
    navigationIcon = {
      if (topAppBarData.navigationIconId != null) {
        IconButton(
          onClick = topAppBarData.onNavigationIconClick,
        ) {
          Icon(
            painter = painterResource(topAppBarData.navigationIconId),
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
          onClick = topAppBarData.onActionIconClick,
        ) {
          Icon(
            painter = painterResource(topAppBarData.actionIconId),
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
fun CustomTopAppBarPreview(
  @PreviewParameter(CustomTopAppBarProvider::class) topAppBarData: TopAppBarData,
) {
  CustomTopAppBar(topAppBarData = topAppBarData)
}