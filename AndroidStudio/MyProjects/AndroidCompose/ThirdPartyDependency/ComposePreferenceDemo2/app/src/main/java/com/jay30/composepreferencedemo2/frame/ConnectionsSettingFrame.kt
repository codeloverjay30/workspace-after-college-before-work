package com.jay30.composepreferencedemo2.frame

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.jay30.composepreferencedemo2.MyTopAppBar
import com.jay30.composepreferencedemo2.R
import me.zhanghai.compose.preference.ProvidePreferenceLocals
import me.zhanghai.compose.preference.switchPreference

@Composable
fun ConnectionSettingFrame(
    navController: NavHostController
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(stringResource(R.string.connections_topappbar_title))
                },
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        ProvidePreferenceLocals {
            // Other composables wrapping the LazyColumn ...
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                switchPreference(
                    key = context.getString(R.string.wifi_toggle_button_key),
                    defaultValue = false,
                    title = { Text(text = context.getString(R.string.wifi_title)) },
                    summary = { Text(text = if (it) "On" else "Off") }
                )
            }
        }
    }
}