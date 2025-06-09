plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.kotlin.parclization)
  alias(libs.plugins.dagger)
}

android {
  namespace = "pl.bla.dev.travelplanner"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    applicationId = "pl.bla.dev.travelplanner"
    minSdk = libs.versions.minSdk.get().toInt()
    targetSdk = libs.versions.targetSdk.get().toInt()
    versionCode = libs.versions.versionCode.get().toInt()
    versionName = libs.versions.versionName.get()

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = libs.versions.jvmTarget.get()
  }
  buildFeatures {
    compose = true
  }
}

dependencies {
  implementation(project(":common:ui"))
  implementation(project(":common:permissions"))
  implementation(project(":common:activityconnector"))
  implementation(project(":common:intents"))
  implementation(project(":common:core"))
  implementation(project(":common:loader"))
  implementation(project(":common:storage"))
  implementation(project(":common:security"))

  implementation(project(":feature:auth"))
  implementation(project(":feature:dashboard"))
  implementation(project(":feature:user"))
  implementation(project(":feature:user:contract"))

  implementation(project(":be:backendservice"))
  implementation(project(":be:backendservice:contract"))

  implementation(libs.lifecycle.viewmodel.compose)
  implementation(libs.lifecycle.viewmodel)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  implementation(libs.squareup.okhttp3)
  implementation(libs.kotlinx.coroutines)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.androidx.work)
  implementation(libs.androidx.hilt.navigation.compose)
  implementation(libs.androidx.hilt.work)
  implementation(libs.dagger.hilt)
  ksp(libs.dagger.hilt.compiler)
  implementation(libs.gson)
  implementation(libs.osmdroid.android)
  implementation(libs.osmdroid.compose)
  implementation(libs.androidx.datastore)
  implementation(libs.androidx.room.runtime)
  implementation(libs.androidx.room.ktx)
  ksp(libs.androidx.room.compiler)
  implementation(libs.database.sqlcipher)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}