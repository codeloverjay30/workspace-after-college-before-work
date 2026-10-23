package com.tibame.tip101.exercise.exer6_03

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(
    navController: NavHostController,
){
    val userIdLabel = stringResource(R.string.user_id_label)
    val passwordLabel = stringResource(R.string.password_label)

    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        TextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text(userIdLabel)},
            modifier = Modifier
                .height(50.dp),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = userIdLabel,
                )
            },
        )
        Spacer(modifier = Modifier.height(8.dp).fillMaxWidth())
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(passwordLabel)},
            modifier = Modifier
                .height(50.dp),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = passwordLabel,
                )
            },
        )
        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
        Button(
            onClick = {
                if(userId.isNotBlank() && password.isNotBlank()) {
                    message = ""
                    navController.navigate(Screen.DeviceScreen.name)
                    return@Button
                }
                message = "Error!!!"
            },
        ){
            Text(stringResource(R.string.login_button))
        }
        Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
        Text(message)
    }
}