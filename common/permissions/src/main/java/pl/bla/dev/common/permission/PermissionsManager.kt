package pl.bla.dev.common.permission

import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import pl.bla.dev.common.activityconnector.ActivityConnector
import pl.bla.dev.common.activityconnector.ActivityForResult
import pl.bla.dev.common.permission.domain.mapper.AppPermissionMapper
import pl.bla.dev.common.permission.domain.model.AppPermission
import pl.bla.dev.common.permission.domain.model.PermissionResult

interface PermissionsActivityConnector : ActivityConnector

interface PermissionsManager {
  suspend fun requestPermission(permission: AppPermission): PermissionResult
  suspend fun isPermissionGranted(permission: AppPermission): Boolean
}

class PermissionManagerImpl(
  private val appPermissionMapper: AppPermissionMapper,
): ActivityForResult<Array<String>, Map<String, Boolean>>(), PermissionsManager, PermissionsActivityConnector  {

  override val resultContract: ActivityResultContract<Array<String>, Map<String, Boolean>> =
    ActivityResultContracts.RequestMultiplePermissions()

  override suspend fun requestPermission(permission: AppPermission): PermissionResult {
    launchActivityForResult(
      intent = appPermissionMapper(permission)
    ).let { result ->
      return when (result.values.all { it }) {
        true -> PermissionResult.GRANTED
        false -> {
          result.entries.forEach { (permissionString, granted) ->
            if (granted == false) {
              if (activity?.shouldShowRequestPermissionRationale(permissionString) == false) {
                return PermissionResult.DENIED_FOREVER
              }
            }
          }

          return PermissionResult.DENIED
        }
      }
    }
  }

  override suspend fun isPermissionGranted(permission: AppPermission): Boolean =
    appPermissionMapper(permission).all { mappedPermission ->
      when (activity?.checkSelfPermission(mappedPermission)) {
        PackageManager.PERMISSION_GRANTED -> PermissionResult.GRANTED
        PackageManager.PERMISSION_DENIED -> PermissionResult.DENIED
        else -> PermissionResult.DENIED_FOREVER
      } == PermissionResult.GRANTED
    }

}