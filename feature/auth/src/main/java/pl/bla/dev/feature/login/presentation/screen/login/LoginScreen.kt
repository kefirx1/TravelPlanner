package pl.bla.dev.feature.login.presentation.screen.login

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.bla.dev.common.ui.R
import pl.bla.dev.common.ui.componenst.basescaffold.BaseScaffold
import pl.bla.dev.common.ui.componenst.button.LargeButton
import pl.bla.dev.common.ui.componenst.emptyscreen.EmptyScreen
import pl.bla.dev.common.ui.componenst.icon.CustomImage
import pl.bla.dev.common.ui.componenst.icon.ImageSize
import pl.bla.dev.common.ui.componenst.input.TextField
import pl.bla.dev.common.ui.componenst.text.CustomText

@Composable
fun LoginScreen(viewModel: LoginVM) {
  val state by viewModel.screenData.collectAsStateWithLifecycle()

  when (val screenData = state) {
    is LoginVM.ScreenData.Initial -> {
      BackHandler {
        screenData.onBackClick()
      }

      EmptyScreen()
    }
    is LoginVM.ScreenData.LoginScreen -> LoginScreenContent(
      data = screenData,
    )
    is LoginVM.ScreenData.RegistrationScreen -> RegistrationScreenContent(
      data = screenData,
    )
  }
}

@Composable
fun LoginScreenContent(
  data: LoginVM.ScreenData.LoginScreen,
) {

  BackHandler {
    data.onBackClick()
  }

  BaseScaffold(
    content = {
      Column(
        modifier = Modifier
          .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Spacer(modifier = Modifier.height(70.dp))

        Row(
          verticalAlignment = Alignment.CenterVertically,
        ) {
          CustomImage(
            iconRes = R.mipmap.ic_travel_planner_logo,
            imageSize = ImageSize.MEDIUM,
          )
          Spacer(modifier = Modifier.width(20.dp))

          CustomText(
            text = data.appName,
            style = MaterialTheme.typography.headlineLarge,
            customSize = 40.sp,
          )
        }
        Spacer(modifier = Modifier.height(80.dp))

        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp),
          horizontalAlignment = Alignment.Start,
        ) {
          CustomText(
            text = data.welcomeLabel,
            style = MaterialTheme.typography.titleLarge,
          )
          Spacer(modifier = Modifier.height(20.dp))
        }

        TextField(
          textFieldData = data.textFieldData,
        )
      }
    },
    bottomBar = {
      Column(
        modifier = Modifier.padding(
          horizontal = 20.dp,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        LargeButton(buttonData = data.buttonData)
      }
    }
  )
}

@Composable
fun RegistrationScreenContent(
  data: LoginVM.ScreenData.RegistrationScreen,
) {

  BackHandler {
    data.onBackClick()
  }

  BaseScaffold(
    content = {
      Column(
        modifier = Modifier
          .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Spacer(modifier = Modifier.height(70.dp))

        Row(
          verticalAlignment = Alignment.CenterVertically,
        ) {
          CustomImage(
            iconRes = R.mipmap.ic_travel_planner_logo,
            imageSize = ImageSize.MEDIUM,
          )
          Spacer(modifier = Modifier.width(20.dp))

          CustomText(
            text = data.appName,
            style = MaterialTheme.typography.headlineLarge,
            customSize = 40.sp,
          )
        }
        Spacer(modifier = Modifier.height(80.dp))

        CustomText(
          text = data.appDescriptionLabel,
          style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))

        CustomText(
          text = data.appDescriptionBody,
          style = MaterialTheme.typography.titleLarge,
        )
      }

    },
    bottomBar = {
      Column(
        modifier = Modifier.padding(
          horizontal = 20.dp,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        LargeButton(buttonData = data.buttonData)
      }
    }
  )
}

