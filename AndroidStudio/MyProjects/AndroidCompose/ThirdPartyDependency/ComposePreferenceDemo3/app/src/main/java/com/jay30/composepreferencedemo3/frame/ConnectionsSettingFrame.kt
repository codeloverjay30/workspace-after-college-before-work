package com.jay30.composepreferencedemo3.frame

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.jay30.composepreferencedemo3.MyTopAppBar
import com.jay30.composepreferencedemo3.R
import com.jay30.composepreferencedemo3.enumclass.MainScreenEnum
import me.zhanghai.compose.preference.Preference
import me.zhanghai.compose.preference.ProvidePreferenceLocals
import me.zhanghai.compose.preference.SwitchPreference

@Composable
fun ConnectionSettingFrame(
    navController: NavHostController
) {
    val context = LocalContext.current

    var switched by remember { mutableStateOf(false) }

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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
                item {
                    SwitchPreference(
                        value = switched,
                        onValueChange = { switched = it},
                        title = { Text(text = context.getString(R.string.wifi_title)) },
                        icon = {
                            IconButton(
                                onClick = {
                                    navController.navigate(MainScreenEnum.CONNECTION_SETTING_FRAME.name)
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_wifi_24),
                                    contentDescription = null
                                )
                            }
                        },
                        summary = { Text(text = if (switched) "On" else "Off") }
                    )
                }

                item{
                    TextButton(onClick = {
                        navController.navigate(MainScreenEnum.SIM_SETTING_FRAME.name)
                    }) {
                        Preference(
                            title = { Text(text = context.getString(R.string.sim_topappbar_title)) },
                            icon = {}
                        )
                    }
                }
            }
        }
    }
}

