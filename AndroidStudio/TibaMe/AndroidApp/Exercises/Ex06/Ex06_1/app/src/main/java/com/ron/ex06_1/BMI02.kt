package com.ron.ex06_1

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ron.ex06_1.ui.theme.Ex06_1Theme
import kotlin.math.pow

@Composable
fun BMI02(
    navController: NavHostController,
    height: String,
    weight: String
) {
    val context = LocalContext.current
    val message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(
                id = R.string.result_formatted,
                height,
                weight,
                bmiValue(weight, height),
                bmiCategory(context, bmiValue(weight, height))
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = stringResource(id = R.string.back_button))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
    }
}

/**
 * 計算BMI值
 */
private fun bmiValue(weight: String, height: String): Double {
    return weight.toDouble() / ((height.toDouble() / 100).pow(2))
}

/**
 * 依據BMI值分類
 */
private fun bmiCategory(context: Context, value: Double): String {
    return if (value < 18.5) {
        context.getString(R.string.underweight)
    } else if (value < 25) {
        context.getString(R.string.normal_weight)
    } else if (value < 30) {
        context.getString(R.string.overweight)
    } else {
        context.getString(R.string.obese)
    }
}

@Preview(showBackground = true)
@Composable
fun Screen02Preview() {
    Ex06_1Theme {
        BMI02(rememberNavController(), height = "180", weight = "75")
    }
}