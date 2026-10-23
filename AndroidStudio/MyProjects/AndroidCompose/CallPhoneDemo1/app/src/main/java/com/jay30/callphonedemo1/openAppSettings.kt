package com.jay30.callphonedemo1

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

/** 開啟app設定 */
fun openAppSettings(packageName: String, context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", packageName, null)
    }
    context.startActivity(intent)
}