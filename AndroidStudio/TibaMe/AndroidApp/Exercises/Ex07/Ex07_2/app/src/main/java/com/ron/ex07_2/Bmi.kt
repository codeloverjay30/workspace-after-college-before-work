package com.ron.ex07_2

import java.io.Serializable
import kotlin.math.pow

class Bmi(private var height: Double, private var weight: Double) : Serializable {
    private val value: Double
        get() = weight / (height / 100).pow(2.0)

    override fun toString(): String {
        var text = "Height: $height\nWeight: $weight\n"
        text += "BMI value: %.2f%n".format(value)
        text +=
            if (value >= 30) "Obesity"
            else if (value >= 25) "Overweight"
            else if (value >= 18.5) "Normal weight"
            else "Underweight"
        return text
    }
}