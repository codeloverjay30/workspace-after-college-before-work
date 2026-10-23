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
import me.zhanghai.compose.preference.PreferenceCategory
import me.zhanghai.compose.preference.ProvidePreferenceLocals

@Composable
fun AccountListSettingFrame(
    navController: NavHostController,
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(stringResource(R.string.account_list_topappbar_title))
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
            ) {
                item {
                    PreferenceCategory(
                        title = { Text(text = context.getString(R.string.account_topappbar_title)) },
                    )
                }

                item {
                    TextButton(
                        onClick = {
                            navController.navigate(MainScreenEnum.ACCOUNT_SETTING_FRAME.name)
                        }
                    ) {
                        Preference(
                            title = { Text(text = context.getString(R.string.account_topappbar_title)) },
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_account_circle_24),
                                    contentDescription = null,
                                )
                            },
                            summary = { Text(text = stringResource(R.string.account_topappbar_title)) }
                        )
                    }
                }
            }
        }
    }
}