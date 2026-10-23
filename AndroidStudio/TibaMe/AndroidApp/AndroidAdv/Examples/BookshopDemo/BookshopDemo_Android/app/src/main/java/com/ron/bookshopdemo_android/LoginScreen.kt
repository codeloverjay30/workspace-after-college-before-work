package com.ron.bookshopdemo_android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import com.ron.bookshopdemo_android.ui.theme.BookshopDemo_AndroidTheme

@Composable
fun LoginScreen(
    userVM: UserVM = viewModel(),
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var uid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.7f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = uid,
                onValueChange = { uid = it },
                label = { Text(text = stringResource(id = R.string.uid)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = stringResource(id = R.string.password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    // 檢查帳密
                    coroutineScope.launch {
                        val logged = userVM.login(uid, password)
                        if (logged) {
                            onLoginSuccess()
                        } else {
                            message = "Logging failed"
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.login))
            }

            Button(
                onClick = {
                    uid = ""
                    password = ""
                    message = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.clear))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "")
                TextButton(
                    onClick = {
                        onRegisterClick()
                    },
                ) {
                    Text(text = stringResource(id = R.string.new_user))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = message)

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    BookshopDemo_AndroidTheme {
        LoginScreen(onLoginSuccess = {}, onRegisterClick = {})
    }
}