package com.tibame.tip101.exercise.exer6_01.ui.bmi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PlatformImeOptions
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.tibame.tip101.exercise.exer6_01.enumclass.ScreenEnum
import com.tibame.tip101.exercise.exer6_01.util.bmi.BmiUtil

@Composable
fun BmiScreen(
    navController: NavHostController,
) {
    var heightText by remember { mutableStateOf("1") }
    var weightText by remember { mutableStateOf("1") }
    var message by remember { mutableStateOf("") }

    val keyboardOptions = KeyboardOptions.Default.copy(
        capitalization = KeyboardCapitalization.None,
        autoCorrectEnabled = true,
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Default,
        platformImeOptions = PlatformImeOptions(null),
        showKeyboardOnFocus = true,
        hintLocales = LocaleList.current,
    )

    Column {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = heightText,
                onValueChange = { text: String ->
                    if (text.isDigitsOnly()) {
                        heightText = text
                    }
                },
                label = { Text("Height") },
                keyboardOptions = keyboardOptions,
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = weightText,
                onValueChange = { text: String ->
                    if (text.isDigitsOnly()) {
                        weightText = text
                    }
                },
                label = { Text("Weight") },
                keyboardOptions = keyboardOptions,
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                    if (BmiUtil.isValid(heightText, weightText)) {
                        message = ""
                        navController.navigate(
                            route = "${ScreenEnum.BMI_RESULT_SCREEN.name}/${heightText}/${weightText}"
                        )
                        return@Button
                    }
                    message = "Error"
                }
            ) {
                Text("Submit")
            }

            Text(message)
        }
    }
}