package pl.bla.dev.feature.login.presentation.screen.registration

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.common.ui.componenst.basescaffold.BaseScaffold
import pl.bla.dev.common.ui.componenst.button.LargeButton
import pl.bla.dev.common.ui.componenst.divider.Divider
import pl.bla.dev.common.ui.componenst.input.TextField
import pl.bla.dev.common.ui.componenst.tab.CustomTopAppBar
import pl.bla.dev.common.ui.componenst.tab.TopAppBarData
import pl.bla.dev.common.ui.componenst.text.CustomText

@Composable
fun RegistrationScreen(viewModel: RegistrationVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()

  when (val screenData = state) {
    is RegistrationVM.ScreenData -> RegistrationScreenContent(
      data = screenData,
    )
  }
}

@Composable
fun RegistrationScreenContent(
  data: RegistrationVM.ScreenData,
) {
  BackHandler {
    data.onBackClick()
  }

  BaseScaffold(
    topBar = {
      CustomTopAppBar(topAppBarData = data.tabData)
    },
    content = {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        CustomText(
          text = data.screenDescription,
          style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = Modifier.height(50.dp))

        TextField(
          textFieldData = data.nameInput,
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
          textFieldData = data.emailInput,
        )
        Divider(spacer = 30.dp)

        TextField(
          textFieldData = data.passwordInput,
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
          textFieldData = data.passwordRepeatInput,
        )
        Spacer(modifier = Modifier.height(150.dp))
      }
    },
    bottomBar = {
      Column(
        modifier = Modifier.padding(
          horizontal = 20.dp,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        LargeButton(buttonData = data.registerButtonData)
      }
    }
  )

}