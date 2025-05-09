package pl.bla.dev.common.ui.componenst.basescaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    modifier = modifier
      .fillMaxSize()
      .background(
        color = AppColors.grey,
      ),
    topBar = topBar,
    content = { padding ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(paddingValues = padding)
          .padding(
            horizontal = 20.dp,
          )
      ) {
        content()
      }
    },
    bottomBar = bottomBar,
    floatingActionButton = fabData?.fab ?: {},
    floatingActionButtonPosition = fabData?.fabPosition ?: FabPosition.End,
  )
}