package pl.bla.dev.common.ui.componenst.basescaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.bla.dev.common.ui.theming.AppColors

data class FabData(
  val fab: @Composable () -> Unit = {},
  val fabPosition: FabPosition = FabPosition.End,
)

@Composable
fun BaseScaffold(
  modifier: Modifier = Modifier,
  topBar: @Composable () -> Unit = {},
  bottomBar: @Composable () -> Unit = {},
  fabData: FabData? = null,
  content: @Composable () -> Unit,
) {
  Scaffold(
    modifier = modifier.imePadding(),
    contentWindowInsets = WindowInsets.safeDrawing,
    topBar = {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .background(color = AppColors.black),
      ) {
        topBar()
      }
    },
    content = { padding ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .background(color = AppColors.grey)
          .padding(paddingValues = padding)
          .padding(
            horizontal = 20.dp,
            vertical = 5.dp,
          )
      ) {
        content()
      }
    },
    bottomBar = {
      Column(
        modifier = Modifier
          .windowInsetsPadding(insets = WindowInsets.navigationBars)
          .fillMaxWidth()
          .background(color = AppColors.grey)
      ) {
        bottomBar()
      }
    },
    floatingActionButton = fabData?.fab ?: {},
    floatingActionButtonPosition = fabData?.fabPosition ?: FabPosition.End,
  )
}