package pl.bla.dev.common.core.navigation

interface Destination {
  val route: String
    get() = this.toString()
}
