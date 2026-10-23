package com.jay30.callphonedemo1

import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainFrame() {
    val context = LocalContext.current

    val permissionState = rememberPermissionState(permission = android.Manifest.permission.CALL_PHONE)
    if(!permissionState.status.isGranted){
        TryToGrantPermission(
            context = context,
            permissionState = permissionState,
            permissionName = stringResource(R.string.call_phone)
        )
    }else {

        var phoneNumber by remember { mutableStateOf("") }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text(stringResource(R.string.phone_number_label)) },
                    maxLines = 1,
                    modifier = Modifier.size(400.dp, 60.dp)
                )
            }

            item {
                Button(
                    onClick = {
                        val intent = Intent(ACTION_CALL).apply {
                            setData(Uri.parse("tel:${phoneNumber}"))
                        }
                        context.startActivity(intent)
                        Toast.makeText(
                            context,
                            context.getString(R.string.call_phone),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {
                    Text(stringResource(R.string.call_phone))
                }
            }
        }
    }
}