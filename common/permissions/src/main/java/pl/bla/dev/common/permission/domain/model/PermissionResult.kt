package pl.bla.dev.common.permission.domain.model

sealed interface PermissionResult {
  data object GRANTED : PermissionResult
  data object DENIED : PermissionResult
  data object DENIED_FOREVER : PermissionResult
}
