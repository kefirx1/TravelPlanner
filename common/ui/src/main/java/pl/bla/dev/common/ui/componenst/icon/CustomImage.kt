package pl.bla.dev.common.ui.componenst.icon

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


enum class ImageSize(val size: Dp) {
  SMALL(size = 15.dp),
  MEDIUM(size = 50.dp),
  LARGE(size = 100.dp),
  EXTRA_LARGE(size = 150.dp),
}

@Composable
fun CustomImage(
  iconRes: Int,
  imageSize: ImageSize = ImageSize.MEDIUM,
  contentDescription: String? = null
) {
  AsyncImage(
    modifier = Modifier.size(imageSize.size),
    model = iconRes,
    contentDescription = contentDescription,
  )
}