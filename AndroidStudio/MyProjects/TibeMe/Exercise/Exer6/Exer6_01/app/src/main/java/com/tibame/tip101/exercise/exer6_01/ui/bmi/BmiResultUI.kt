package com.tibame.tip101.exercise.exer6_01.ui.bmi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tibame.tip101.exercise.exer6_01.util.bmi.BmiUtil

@Composable
fun BmiResultScreen(
    navController: NavHostController,
    heightText: String,
    weightText: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val height = heightText.toIntOrNull() ?: 1
        val weight = weightText.toIntOrNull() ?: 1
        val bmi = BmiUtil.calculateBMI(height, weight)
        val healthStatus = BmiUtil.getHealthStatus(bmi)
        Text("Height:$height")
        Text("Weight:$weight")
        Text("BMI:$bmi")
        Text(healthStatus)
    }
}
