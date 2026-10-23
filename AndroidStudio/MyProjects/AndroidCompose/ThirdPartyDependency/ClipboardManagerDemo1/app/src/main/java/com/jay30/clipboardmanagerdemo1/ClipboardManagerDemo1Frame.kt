package com.jay30.clipboardmanagerdemo1

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString

@Composable
fun ClipboardManagerDemo1Frame() {
    val context = LocalContext.current
    // Retrieve a ClipboardManager object
    val clipboardManager = LocalClipboardManager.current

    var annotatedString = AnnotatedString("Hello, clipboard")

    var text1 by remember { mutableStateOf("") }

    var text2 by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            LazyRow {
                item {
                    Button(
                        onClick = {
                            Toast.makeText(
                                context,
                                context.getString(R.string.copy_button_clicked),
                                Toast.LENGTH_SHORT
                            ).show()
                            annotatedString = AnnotatedString(text1)
                            clipboardManager.setText(annotatedString)
                        }
                    ) {
                        Text("Click to copy a text")
                    }
                }

                item{
                    Button(
                        onClick = {
                            Toast.makeText(
                                context,
                                context.getString(R.string.paste_button_clicked),
                                Toast.LENGTH_SHORT
                            ).show()
                            text2 = clipboardManager.getText().toString()
                        }
                    ) {
                        Text("Click to copy a text")
                    }
                }
            }
        }

        item {
            OutlinedTextField(
                value = text1,
                onValueChange = { text1 = it }
            )
        }

        item {
            OutlinedTextField(
                value = text2,
                onValueChange = { text2 = it }
            )
        }
    }
}