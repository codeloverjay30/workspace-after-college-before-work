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
import me.zhanghai.compose.preference.FooterPreference
import me.zhanghai.compose.preference.Preference
import me.zhanghai.compose.preference.ProvidePreferenceLocals

@Composable
fun MainFrame(
    navController: NavHostController
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(stringResource(R.string.main_frame_topappbar_title))
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
                    TextButton(
                        onClick = {
                            navController.navigate(MainScreenEnum.CONNECTION_SETTING_FRAME.name)
                        }
                    ) {
                        Preference(
                            title = { Text(text = context.getString(R.string.connections_topappbar_title)) },
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_wifi_24),
                                    contentDescription = null,
                                )
                            },
                            summary = { Text(text = stringResource(R.string.wifi_title)) }
                        )
                    }
                }
                item {
                    TextButton(
                        onClick = {
                            navController.navigate(MainScreenEnum.SOUNDS_AND_VIBRATION_SETTING_FRAME.name)
                        }
                    ) {
                        Preference(
                            title = { Text(text = context.getString(R.string.sounds_and_vibration_topappbar_title)) },
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_volume_up_24),
                                    contentDescription = null,
                                )
                            },
                            summary = { Text(text = stringResource(R.string.sounds_and_vibration_topappbar_title)) }
                        )
                    }
                }

                item {
                    TextButton(
                        onClick = {
                            navController.navigate(MainScreenEnum.APP_SETTING_FRAME.name)
                        }
                    ) {
                        Preference(
                            title = { Text(text = context.getString(R.string.app_topappbar_title)) },
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_apps_24),
                                    contentDescription = null,
                                )
                            },
                            summary = { Text(text = stringResource(R.string.app_topappbar_title)) }
                        )
                    }
                }

                item {
                    TextButton(
                        onClick = {
                            navController.navigate(MainScreenEnum.ACCOUNT_LIST_SETTING_FRAME.name)
                        }
                    ) {
                        Preference(
                            title = { Text(text = context.getString(R.string.account_list_topappbar_title)) },
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_account_circle_24),
                                    contentDescription = null,
                                )
                            },
                            summary = { Text(text = stringResource(R.string.account_list_topappbar_title)) }
                        )
                    }
                }

                item {
                    FooterPreference(
                        summary = { Text(stringResource(R.string.footer_summary)) }
                    )
                }
            }
        }
    }
}