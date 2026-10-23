package com.jay30.annotationdemo1

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DontMemoize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

@Composable
fun AnnotationDemo1Frame() {
    val context = LocalContext.current

    // memoization
    val lambdaWithoutDontMemoizeAnnotation = { x: Int ->
        x + 1
    }

    // avoid memoization
    val lambdaWithDontMemoizeAnnotation = @DontMemoize { x: Int ->
        x + 1
    }

    var integer1 = 0
    var integer2 = integer1
    var integer3 by remember { mutableIntStateOf(0) }
    var integer4 by remember { mutableIntStateOf(integer3) }
    val integer5 = remember { mutableIntStateOf(0) }
    val integer6 = remember { mutableIntStateOf(integer5.intValue) }
    var integer7 = remember { 0 }
    var integer8 = integer7

   LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Button(
                onClick = {
                    integer1 = lambdaWithoutDontMemoizeAnnotation(integer1)
                    integer2 = lambdaWithoutDontMemoizeAnnotation(integer2)
                    integer3 = lambdaWithoutDontMemoizeAnnotation(integer3)
                    integer4 = lambdaWithoutDontMemoizeAnnotation(integer4)
                    integer5.intValue = lambdaWithoutDontMemoizeAnnotation(integer5.intValue)
                    integer6.intValue = lambdaWithoutDontMemoizeAnnotation(integer6.intValue)
                    integer7 = lambdaWithoutDontMemoizeAnnotation(integer7)
                    integer8 = lambdaWithoutDontMemoizeAnnotation(integer8)

                    Toast.makeText(
                        context,
                        "${context.getString(R.string.button1)} was pressed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            ) {
                Text(stringResource(R.string.increase_button1))
            }
        }

        item {
            Button(
                onClick = {
                    integer1 = lambdaWithDontMemoizeAnnotation(integer1)
                    integer2 = lambdaWithDontMemoizeAnnotation(integer2)
                    integer3 = lambdaWithDontMemoizeAnnotation(integer3)
                    integer4 = lambdaWithDontMemoizeAnnotation(integer4)
                    integer5.intValue = lambdaWithDontMemoizeAnnotation(integer5.intValue)
                    integer6.intValue = lambdaWithDontMemoizeAnnotation(integer6.intValue)
                    integer7 = lambdaWithDontMemoizeAnnotation(integer7)
                    integer8 = lambdaWithDontMemoizeAnnotation(integer8)

                    Toast.makeText(
                        context,
                        "${context.getString(R.string.button2)} was pressed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            ) {
                Text(stringResource(R.string.increase_button2))
            }
        }

        item {
            Text("integer1:${integer1}")
        }
        item {
            Text("integer2:${integer2}")
        }
        item {
            Text("integer3:${integer3}")
        }
        item {
            Text("integer4:${integer4}")
        }
        item {
            Text("integer5.intValue:${integer5.intValue}")
        }
        item {
            Text("integer6.intValue:${integer6.intValue}")
        }
        item {
            Text("integer7:${integer7}")
        }
        item {
            Text("integer8:${integer8}")
        }
    }
}