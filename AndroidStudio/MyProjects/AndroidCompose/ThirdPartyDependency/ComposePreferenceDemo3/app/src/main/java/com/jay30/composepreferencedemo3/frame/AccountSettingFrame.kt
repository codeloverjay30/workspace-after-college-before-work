package com.jay30.composepreferencedemo3.frame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.jay30.composepreferencedemo3.MyTopAppBar
import com.jay30.composepreferencedemo3.R
import me.zhanghai.compose.preference.ProvidePreferenceLocals
import me.zhanghai.compose.preference.TextFieldPreference
import me.zhanghai.compose.preference.TwoTargetIconButtonPreference
import me.zhanghai.compose.preference.TwoTargetPreference
import me.zhanghai.compose.preference.TwoTargetSwitchPreference

@Composable
fun AccountSettingFrame(
    navController: NavHostController,
) {
    val context = LocalContext.current

    var accountName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var changePasswordIconIsClicked by remember { mutableStateOf(false) }
    var hasToEnterPasswordForSignIn by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var changeEmailButtonIsClicked by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(stringResource(R.string.account_topappbar_title))
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

                // for account name
                item {
                    TextFieldPreference(
                        value = accountName,
                        onValueChange = { },
                        title = { Text("${stringResource(R.string.account_name)}:") },
                        textToValue = { accountName = it },
                        summary = { Text(accountName) }
                    )
                }

                // for password
                item {
                    TwoTargetIconButtonPreference(
                        title = { Text("${stringResource(R.string.password)}:") },
                        iconButtonIcon = {
                            Icon(
                                painter = painterResource(R.drawable.baseline_password_24),
                                contentDescription = null
                            )
                        },
                        onIconButtonClick = {
                            changePasswordIconIsClicked = true
                        },
                        summary = { Text(password) }
                    )
                }

                // has to enter password once sign in is needed.
                item {
                    TwoTargetSwitchPreference(
                        value = hasToEnterPasswordForSignIn,
                        onValueChange = { hasToEnterPasswordForSignIn = it },
                        title = { Text(stringResource(R.string.need_enter_password_for_sign_in)) },
                        summary = { Text(text = if (hasToEnterPasswordForSignIn) "Yes" else "No") }
                    )
                }

                // for email
                item {
                    TwoTargetPreference(
                        title = { Text("${stringResource(R.string.email_title)}:") },
                        secondTarget = {
                            TextButton(
                                onClick = {
                                    changeEmailButtonIsClicked = true
                                }) {
                                Text(stringResource(R.string.change_email))
                            }
                        },
                        summary = { Text(email) }
                    )
                }
            }
        }
    }

    if (changePasswordIconIsClicked) {
        Dialog(
            onDismissRequest = {
                changePasswordIconIsClicked = false
            }
        ) {
            LazyColumn(
                modifier = Modifier.size(400.dp, 350.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                item {
                    Text(stringResource(R.string.change_password_title))
                }

                item {
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(stringResource(R.string.change_password_label)) }
                    )
                }
            }
        }
    }

    if (changeEmailButtonIsClicked) {
        Dialog(
            onDismissRequest = {
                changeEmailButtonIsClicked = false
            }
        ) {
            LazyColumn(
                modifier = Modifier.size(400.dp, 350.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                item {
                    Text(stringResource(R.string.change_email_title))
                }

                item {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(stringResource(R.string.change_email_label)) }
                    )
                }
            }
        }
    }
}