package com.jay30.smsmanagerdemo1

import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
fun MainFrame(){
    val context = LocalContext.current

    val smsManager = SmsManager.getDefault()

    var phoneNumber by remember { mutableStateOf("") }
    var messsage by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        item {
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text(stringResource(R.string.phone_number_title)) }
            )
        }

        item{
            OutlinedTextField(
                value = messsage,
                onValueChange = { messsage = it },
                label = { Text(stringResource(R.string.message_title)) }
            )
        }

        item {
            Button(
                onClick = {
                    try {
                        smsManager.sendTextMessage(phoneNumber, null, messsage, null, null)
                        Toast.makeText(
                            context,
                            context.getString(R.string.send_message_successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                    }catch (e:Exception){
                        Toast.makeText(
                            context,
                            context.getString(R.string.send_message_failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Text(stringResource(R.string.send))
            }
        }
    }
}