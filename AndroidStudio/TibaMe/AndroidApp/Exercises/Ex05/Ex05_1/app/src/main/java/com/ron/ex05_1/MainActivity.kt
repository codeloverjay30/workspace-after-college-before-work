package com.ron.ex05_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.ex05_1.ui.theme.Ex05_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex05_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

enum class MyShape {
    Circle, Rectangle
}

enum class MyColor {
    Blue, Red
}

@Composable
fun Main(modifier: Modifier = Modifier) {
    var selectedShape by remember { mutableStateOf(MyShape.Circle) }
    var selectedColor by remember { mutableStateOf(MyColor.Blue) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(
                Modifier.selectableGroup(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Shape")
                MyShape.entries.forEach { shape ->
                    Row(
                        Modifier
                            .selectable(
                                selected = (shape == selectedShape),
                                onClick = { selectedShape = shape },
                                role = Role.RadioButton
                            )
                    ) {
                        // 如果preview無法正確顯示選取狀態，請改用模擬器
                        RadioButton(
                            selected = (shape == selectedShape),
                            // 已經由Row偵測onClick並回應，所以RadioButton的onClick可以設為null
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = colorResource(id = R.color.purple_200),
                                unselectedColor = colorResource(id = R.color.teal_200)
                            )
                        )
                        Text(
                            text = shape.toString(),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
            Column(
                Modifier.selectableGroup(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Color")
                MyColor.entries.forEach { color ->
                    Row(
                        Modifier
                            .selectable(
                                selected = (color == selectedColor),
                                onClick = { selectedColor = color },
                                role = Role.RadioButton
                            )
                    ) {
                        // 如果preview無法正確顯示選取狀態，請改用模擬器
                        RadioButton(
                            selected = (color == selectedColor),
                            // 已經由Row偵測onClick並回應，所以RadioButton的onClick可以設為null
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = colorResource(id = R.color.purple_200),
                                unselectedColor = colorResource(id = R.color.teal_200)
                            )
                        )
                        Text(
                            text = color.toString(),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp), contentAlignment = Alignment.Center
        ) {
            DrawShape(shape = selectedShape, color = selectedColor)
        }
    }
}


@Composable
fun DrawShape(shape: MyShape = MyShape.Circle, color: MyColor = MyColor.Blue) {
    Canvas(modifier = Modifier.height(100.dp)) {
        val width = 300f
        when (shape) {
            // drawOval()與drawRect()設定方式相同，較好對齊。而drawOval()寬高設定一樣即為圓形
            MyShape.Circle -> drawOval(
                color = when (color) {
                    MyColor.Blue -> Color.Blue
                    MyColor.Red -> Color.Red
                },
                /* 因為上面父容器Box設定內容物為置中對齊，所以原點為中心點，
                    因此topLeft的x設為(-width / 2)會在圖形水平中點，才能讓橢圓形水平置中 */
                topLeft = Offset(-width / 2f, 0f),
                size = Size(width, width)
            )

            MyShape.Rectangle -> drawRoundRect(
                color = when (color) {
                    MyColor.Blue -> Color.Blue
                    MyColor.Red -> Color.Red
                },
                topLeft = Offset(-width / 2f, 0f),
                size = Size(width, width),
                cornerRadius = CornerRadius(16f)
            )
        }
    }
    Text(text = shape.toString(), color = Color.Yellow)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ex05_1Theme {
        Main()
    }
}