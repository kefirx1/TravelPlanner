package pl.bla.dev.common.activityconnector

import androidx.appcompat.app.AppCompatActivity


interface ActivityConnector {
  fun connect(activity: AppCompatActivity)
}