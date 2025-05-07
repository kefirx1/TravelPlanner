package pl.bla.dev.common.activityconnector

import androidx.activity.ComponentActivity


interface ActivityConnector {
  fun connect(activity: ComponentActivity)
}