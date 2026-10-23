package com.example.dietapp.extension.string

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object StringExtensionTest {
    @Composable
    fun test1(){
        Column {
            val dash = "-"

            val string1 = "cat shaboo"
            val string2 = "`value1`"

            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(dash.repeat(50))

            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(string1.trim(' '))

            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(string1.insertAtBothSide(' '))
            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(string1.hasLeadingAndTrailing('`').toString())


            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(string1.insertAtBothSide('`'))
            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(string1.tryToInsertAtBothSide('`'))

            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(dash.repeat(50))

            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(string2.trim('`'))

            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(string2.insertAtBothSide(' '))
            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(string2.hasLeadingAndTrailing('`').toString())
            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(string2.insertAtBothSide('`'))
            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(string2.tryToInsertAtBothSide('`'))
        }
    }
}