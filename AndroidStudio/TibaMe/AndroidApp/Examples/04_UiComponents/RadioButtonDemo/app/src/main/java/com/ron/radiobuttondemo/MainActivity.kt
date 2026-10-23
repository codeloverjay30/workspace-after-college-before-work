package com.ron.radiobuttondemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.radiobuttondemo.ui.theme.RadioButtonDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RadioButtonDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val radioOptions = listOf("Large", "Medium", "Small")
    /* selectedOption: 取值(getter); onOptionSelected: 設定值(setter)
       val (selectedOption, onOptionSelected):
        宣告selectedOption與onOptionSelected這2個變數去接收remember內的getter與setter */
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.selectableGroup()) {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 如果preview無法正確顯示選取狀態，請改用模擬器
                    RadioButton(
                        selected = (text == selectedOption),
                        // 已經由Row偵測onClick並回應，所以RadioButton的onClick可以設為null
                        onClick = null,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = colorResource(id = R.color.purple_200),
                            unselectedColor = colorResource(id = R.color.teal_200)
                        )
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
        Text(text = selectedOption)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RadioButtonDemoTheme {
        Main()
    }
}