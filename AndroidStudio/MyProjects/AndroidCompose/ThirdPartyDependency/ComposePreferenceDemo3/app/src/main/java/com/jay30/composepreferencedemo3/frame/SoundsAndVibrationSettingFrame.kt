package com.jay30.composepreferencedemo3.frame

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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

@Composable
fun SoundsAndVibrationSettingFrame(
    navController: NavHostController,
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(stringResource(R.string.sounds_and_vibration_topappbar_title))
                },
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        ProvidePreferenceLocals {
            // Other composables wrapping the LazyColumn ...
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                item {
                    TextButton(onClick = {
                        navController.navigate(MainScreenEnum.VOLUME_SETTING_FRAME.name)
                    }) {
                        Preference(
                            title = { Text(text = context.getString(R.string.sounds_and_vibration_topappbar_title)) },
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_volume_up_24),
                                    contentDescription = null,
                                )
                            }
                        )
                    }
                }

                item {
                    TextButton(onClick = {
                        navController.navigate(MainScreenEnum.CALL_VIBRATION_SETTING_FRAME.name)
                    }) {
                        Preference(
                            title = { Text(text = context.getString(R.string.call_vibration_topappbar_title)) },
                            icon = {}
                        )
                    }
                }

                item {
                    TextButton(onClick = {
                        navController.navigate(MainScreenEnum.RINGTONE_SETTING_FRAME.name)
                    }) {
                        Preference(
                            title = { Text(text = context.getString(R.string.ringtone_topappbar_title)) },
                            icon = {}
                        )
                    }
                }
            }
        }
    }
}