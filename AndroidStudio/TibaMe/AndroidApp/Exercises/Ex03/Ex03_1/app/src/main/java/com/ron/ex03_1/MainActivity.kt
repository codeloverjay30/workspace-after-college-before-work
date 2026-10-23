package com.ron.ex03_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ron.ex03_1.ui.theme.Ex03_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex03_1Theme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var number1Str by remember { mutableStateOf("") }
    var number2Str by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = number1Str,
            onValueChange = { number1Str = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            // 輸入的文字對齊末端
            textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.End),
            label = { Text(text = stringResource(id = R.string.number1)) },
            singleLine = true,
            shape = RoundedCornerShape(8.dp)
        )
        OutlinedTextField(
            value = number2Str,
            onValueChange = { number2Str = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            // 輸入的文字對齊末端
            textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.End),
            label = { Text(text = stringResource(id = R.string.number2)) },
            singleLine = true,
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = result,
            style = TextStyle(textAlign = TextAlign.End),
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                val number1 = number1Str.toDouble()
                val number2 = number2Str.toDouble()
                result = "$number1 + $number2 = ${number1 + number2}"
            }) {
                Text(text = "+", fontSize = 24.sp)
            }

            Button(onClick = {
                val number1 = number1Str.toDouble()
                val number2 = number2Str.toDouble()
                result = "$number1 - $number2 = ${number1 - number2}"
            }) {
                Text(text = "-", fontSize = 24.sp)
            }

            Button(onClick = {
                val number1 = number1Str.toDouble()
                val number2 = number2Str.toDouble()
                result = "$number1 x $number2 = ${number1 * number2}"
            }) {
                Text(text = "x", fontSize = 24.sp)
            }

            Button(onClick = {
                val number1 = number1Str.toDouble()
                val number2 = number2Str.toDouble()
                result = "$number1 ÷ $number2 = ${number1 / number2}"
            }) {
                Text(text = "÷", fontSize = 24.sp)
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    // 設定按鈕容器顏色
                    containerColor = Color.Red,
                    // 設定按鈕內容顏色
                    contentColor = Color.White
                ),
                onClick = {
                    number1Str = ""
                    number2Str = ""
                    result = ""
                }) {
                Text(text = "C", fontSize = 24.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex03_1Theme {
        Main()
    }
}