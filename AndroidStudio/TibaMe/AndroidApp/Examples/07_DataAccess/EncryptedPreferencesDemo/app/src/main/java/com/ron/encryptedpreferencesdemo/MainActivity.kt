package com.ron.encryptedpreferencesdemo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.ron.encryptedpreferencesdemo.ui.theme.EncryptedPreferencesDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EncryptedPreferencesDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    val encryptedPreferences = getEncryptedPreferences(context)
    var uid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        uid = encryptedPreferences.getString("uid", "")!!
        password = encryptedPreferences.getString("password", "")!!
        message = context.getString(R.string.uid_password_loaded)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = uid,
            onValueChange = { uid = it },
            label = { Text(text = stringResource(id = R.string.uid)) },
            singleLine = true
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(id = R.string.password)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    encryptedPreferences.edit()
                        .putString("uid", uid)
                        .putString("password", password)
                        .apply()
                    message = context.getString(R.string.uid_password_saved)
                }
            ) {
                Text(text = stringResource(id = R.string.save))
            }
            Button(
                onClick = {
                    uid = encryptedPreferences.getString("uid", "")!!
                    password = encryptedPreferences.getString("password", "")!!
                    message = context.getString(R.string.uid_password_loaded)
                }
            ) {
                Text(text = stringResource(id = R.string.load))
            }
            Button(
                onClick = {
                    uid = ""
                    password = ""
                    message = ""
                }
            ) {
                Text(text = stringResource(id = R.string.clear))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = message)
    }
}

/**
 * 取得可加密的偏好設定檔物件
 */
private fun getEncryptedPreferences(context: Context): SharedPreferences {
    // creating a master key for encryption of shared preferences.
    val masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    // Initialize/open an instance of EncryptedSharedPreferences on below line.
    return EncryptedSharedPreferences.create(
        context,
        // passing a file name to share a preferences
        "encrypted_settings",
        masterKeyAlias,
        // Pref keys are encrypted deterministically with AES256-SIV-CMAC.
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        // Pref values are encrypted with AES256-GCM.
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EncryptedPreferencesDemoTheme {
        Main()
    }
}

