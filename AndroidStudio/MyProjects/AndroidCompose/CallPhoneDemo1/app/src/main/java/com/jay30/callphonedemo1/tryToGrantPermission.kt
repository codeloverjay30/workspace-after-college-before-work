package com.jay30.callphonedemo1

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TryToGrantPermission(
    context: Context,
    permissionState: PermissionState,
    permissionName: String,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(stringResource(R.string.request_permission_text,permissionName))
        Button(onClick = {
            permissionState.launchPermissionRequest()
        }) {
            Text(stringResource(R.string.request_permission,permissionName))
        }

        // 也可以引導使用者去設定頁面授權
        Text(stringResource(R.string.open_settings_text,permissionName))
        Button(onClick = {
            openAppSettings(context.packageName, context)
        }) {
            Text(stringResource(R.string.open_settings))
        }
    }
}