package com.tibame.tip101.exercise.exer3_01

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tibame.tip101.exercise.exer3_01.util.calculator.SimpleCalculator

@Composable
fun SimpleCalculatorScreen(){
    var number1Text by remember { mutableStateOf("") }
    var number2Text by remember { mutableStateOf("") }

    var equationText by remember { mutableStateOf("") }

    var number1 = remember { mutableFloatStateOf(0f) }
    var number2 = remember { mutableFloatStateOf(0f) }

    val PARSE_ERROR_MESSAGE = stringResource(R.string.parse_error_message)
    val ZERO_DIVISION_ERROR_MESSAGE = stringResource(R.string.zero_division_error_message)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        OutlinedTextField(
            value = number1Text,
            onValueChange = { number1Text = it },
            label = { Text("Number1") },
        )

        OutlinedTextField(
            value = number2Text,
            onValueChange = { number2Text = it },
            label = { Text("Number2") },
        )
        Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
        Text(equationText)
        Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ){
            Button(onClick = {
                if(!SimpleCalculator.isValidInput(number1Text,number2Text)){
                    equationText = PARSE_ERROR_MESSAGE
                    return@Button
                }
                val number1 = number1Text.toFloat()
                val number2 = number2Text.toFloat()
                equationText = "${number1} + ${number2} = ${number1+number2}"
            }) {
                Text(text = "+", fontSize = 24.sp)
            }

            Button(onClick = {
                if(!SimpleCalculator.isValidInput(number1Text,number2Text)){
                    equationText = PARSE_ERROR_MESSAGE
                    return@Button
                }
                val number1 = number1Text.toFloat()
                val number2 = number2Text.toFloat()
                equationText = "${number1} - ${number2} = ${number1-number2}"
            }) {
                Text(text = "-", fontSize = 24.sp)
            }

            Button(onClick = {
                if(!SimpleCalculator.isValidInput(number1Text,number2Text)){
                    equationText = PARSE_ERROR_MESSAGE
                    return@Button
                }
                val number1 = number1Text.toFloat()
                val number2 = number2Text.toFloat()
                equationText = "${number1} * ${number2} = ${number1*number2}"
            }) {
                Text(text = "x", fontSize = 24.sp)
            }

            Button(onClick = {
                if(!SimpleCalculator.isValidInput(number1Text,number2Text)){
                    equationText = PARSE_ERROR_MESSAGE
                    return@Button
                }
                val number1 = number1Text.toFloat()
                val number2 = number2Text.toFloat()
                if(SimpleCalculator.canNotDivide(number1,number2)){
                    equationText = ZERO_DIVISION_ERROR_MESSAGE
                    return@Button
                }
                equationText = "${number1} / ${number2} = ${number1/number2}"
            }) {
                Text(text = "÷", fontSize = 24.sp)
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                onClick = {
                    equationText = ""
                    number1Text = ""
                    number2Text = ""
                }) {
                Text(text = "C", fontSize = 24.sp)
            }
        }
    }
}