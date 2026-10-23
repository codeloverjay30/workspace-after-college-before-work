package com.jay30.emailserverdemo1

import android.content.Intent
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
import androidx.core.content.ContextCompat.startActivity

@Composable
fun MainFrame(){
    val context = LocalContext.current

    var emailSend by remember { mutableStateOf("") }
    var emailSubject by remember { mutableStateOf("") }
    var emailBody by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        item {
            OutlinedTextField(
                value = emailSend,
                onValueChange = { emailSend = it },
                label = { Text(stringResource(R.string.sendto_labal))}
            )
        }

        item {
            OutlinedTextField(
                value = emailSubject,
                onValueChange = { emailSubject = it },
                label = { Text(stringResource(R.string.email_subject_title))}
            )
        }

        item {
            OutlinedTextField(
                value = emailBody,
                onValueChange = { emailBody = it },
                placeholder = { Text(stringResource(R.string.email_body_title))}
            )
        }
        item {
            Button(
                onClick = {
                    // define Intent object with action attribute as ACTION_SEND
                    val intent = Intent(Intent.ACTION_SEND)

                    // add three fields to intent using putExtra function
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailSend))
                    intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
                    intent.putExtra(Intent.EXTRA_TEXT, emailBody)
                    intent.type = "message/rfc822"
                    context.startActivity(Intent.createChooser(intent, "Choose an Email client :"))
                }
            ) {
                Text(stringResource(R.string.send_email))
            }
        }
    }
}