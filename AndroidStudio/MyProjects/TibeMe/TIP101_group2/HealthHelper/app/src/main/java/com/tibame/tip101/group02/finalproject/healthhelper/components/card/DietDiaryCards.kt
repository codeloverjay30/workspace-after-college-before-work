package com.tibame.tip101.group02.finalproject.healthhelper.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tibame.tip101.group02.finalproject.healthhelper.classhandler.ClassInspector

@Composable
fun <T : Any> DietDiaryCards(
    cards: List<T>,
    modifier: Modifier = Modifier,
) {
    cards.forEach { card: T ->
        val cls = card.javaClass
        val classInspector = ClassInspector(cls)
        val fieldsName = classInspector.getFieldsName()
        val fieldsValue = classInspector.getFieldsValue(card)
        val zipped = fieldsName.zip(fieldsValue).toMap()
        Card(
            modifier = modifier,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Account",
                    modifier = Modifier.size(40.dp),
                )
                Column {
                    zipped.forEach {
                        Text("${it.key}:${it.value}")
                    }
                }
                Image(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Account",
                    modifier = Modifier.size(40.dp),
                    )
            }
        }
    }
}