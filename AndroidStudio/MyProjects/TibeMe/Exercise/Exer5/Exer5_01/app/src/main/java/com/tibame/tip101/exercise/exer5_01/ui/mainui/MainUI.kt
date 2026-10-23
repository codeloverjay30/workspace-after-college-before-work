package com.tibame.tip101.exercise.exer5_01.ui.mainui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.tibame.tip101.exercise.exer5_01.R
import com.tibame.tip101.exercise.exer5_01.ui.enumclass.Color
import com.tibame.tip101.exercise.exer5_01.ui.enumclass.Shape
import com.tibame.tip101.exercise.exer5_01.ui.util.components.button.radiobutton.MyRadioButton

object MainUI {
    @Composable
    fun popUp(modifier: Modifier = Modifier){
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(top = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val selectedColor = colorResource(id = R.color.purple_200)
            val unSelectedColor = colorResource(id = R.color.teal_200)
            val titleShape = "Shape"
            val shapeList = Shape.entries.toList()
            val selectedOptionShape = remember { mutableStateOf(shapeList[0]) }
            val myRadioButtonShape = MyRadioButton(
                title = titleShape,
                radioOptions = shapeList,
                selectedColor = selectedColor,
                unSelectedColor = unSelectedColor,
                selectedOption = selectedOptionShape,
                onOptionSelected = {selectedOptionShape.value = it}
            )

            val titleColor = "Color"
            val colorList = Color.entries.toList()
            val selectedOptionColor = remember { mutableStateOf(colorList[0]) }
            val myRadioButtonColor = MyRadioButton(
                title = titleColor,
                radioOptions = colorList,
                selectedColor = selectedColor,
                unSelectedColor = unSelectedColor,
                selectedOption = selectedOptionColor,
                onOptionSelected = {selectedOptionColor.value = it}
            )
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                myRadioButtonShape.popUp()
                Spacer(modifier = Modifier.width(20.dp))
                myRadioButtonColor.popUp()
            }
            Spacer(modifier = Modifier.height(30.dp).fillMaxWidth())
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp), contentAlignment = Alignment.Center
            ) {
                MainUI.DrawShape(
                    shape = selectedOptionShape,
                    color = selectedOptionColor,
                )
            }
        }
    }

    @Composable
    fun DrawShape(
        shape: MutableState<Shape> = mutableStateOf(Shape.CIRCLE),
        color: MutableState<Color> = mutableStateOf(Color.BLUE),
    ){
        Canvas(modifier = Modifier.height(100.dp)) {
            val width = 300f
            when (shape.value) {
                // drawOval()與drawRect()設定方式相同，較好對齊。而drawOval()寬高設定一樣即為圓形
                Shape.CIRCLE -> drawOval(
                    color = when (color.value) {
                        Color.BLUE -> androidx.compose.ui.graphics.Color.Blue
                        Color.RED -> androidx.compose.ui.graphics.Color.Red
                    },
                    /* 因為上面父容器Box設定內容物為置中對齊，所以原點為中心點，
                        因此topLeft的x設為(-width / 2)會在圖形水平中點，才能讓橢圓形水平置中 */
                    topLeft = Offset(-width / 2f, 0f),
                    size = Size(width, width)
                )

                Shape.RECTANGLE -> drawRoundRect(
                    color = when (color.value) {
                        Color.BLUE -> androidx.compose.ui.graphics.Color.Blue
                        Color.RED -> androidx.compose.ui.graphics.Color.Red
                    },
                    topLeft = Offset(-width / 2f, 0f),
                    size = Size(width, width),
                    cornerRadius = CornerRadius(16f)
                )
            }
        }
        Text(
            text = shape.value.toString(),
            color = androidx.compose.ui.graphics.Color.Yellow
        )
    }
}