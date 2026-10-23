package com.jay30.keyboardeventswrapper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.nativeKeyCode
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.jay30.keyboardeventswrapper.ui.theme.KeyboardEventsWrapperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KeyboardEventsWrapperTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main(){
    MainScreen()
}

@Composable
fun MainScreen() {
    var message1 by remember { mutableStateOf("") }
    var inputText by remember { mutableStateOf("") }
    var oldInputText by remember { mutableStateOf("") }
    var inputKey by remember { mutableStateOf("") }

    var isLetter by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {

        TextField(
            value = inputText,
            onValueChange = {
                if(it.isNotBlank() &&it.isDigitsOnly()) {
                    inputText = it
                }
                oldInputText = inputText
                            },
            label = { Text("inputText")},
            modifier = Modifier.onKeyEvent { keyEvent ->
                val key = keyEvent.key
                inputKey = key.toString()
                true
            },
            supportingText = {
                Text("input value can only contain")
            }
        )

        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())

        Text("oldInputText:${oldInputText}")
        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())

        Text("inputText:${inputText}")
        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())

        Text(inputKey)
        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())

        Text(isLetter.toString())
        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())

        Text(message1)
        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
    }
}
