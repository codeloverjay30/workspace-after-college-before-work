package com.jay30.localtexttoolbardemo1

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.stringResource

@Composable
fun MainFrame() {
    val context = LocalContext.current

    val activity = context as Activity

    //val toolbar = LocalTextToolbar.current
    var showToolbar by remember { mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        item {
            TextButton(
                onClick = {
                    showToolbar = !showToolbar
                    if (showToolbar) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.should_show_toolbar_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Text(stringResource(R.string.text1))
            }
        }
    }
}