package com.example.dietapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.dietapp.componentsattr.box.BoxAttr
import com.example.dietapp.componentsattr.dropdownmenu.DropdownMenuAttr
import com.example.dietapp.componentsattr.textfield.TextFieldAttr
import com.example.dietapp.resources.stringresources.StringResourcesSingleton
import com.example.dietapp.util.components.dropdown.dropmenu.MyDropdownMenu

object SortDietDiaryUI {
    @Composable
    fun show(){

        Column(
            modifier = Modifier.fillMaxSize()
        ){
            val dateSortingOrderItems = mutableListOf(StringResourcesSingleton.EARLIEST,StringResourcesSingleton.LATEST)
            var dateSortingOrderTextFieldValue by remember { mutableStateOf(TextFieldValue(dateSortingOrderItems[0])) }
            val textFieldAttrForDate = TextFieldAttr(
                value = dateSortingOrderTextFieldValue,
                label = { Text(StringResourcesSingleton.SORT_FOR_DATE) },
                placeholder = { Text("") },
                onValueChange = { dateSortingOrderTextFieldValue = it },
                modifier = Modifier.height(60.dp).width(100.dp)
            )

            val boxAttr = BoxAttr(
                modifier = Modifier.height(0.dp).width(100.dp)
            )

            val dropdownMenuAttr = DropdownMenuAttr(
                onDismissRequest = {},
                modifier = Modifier.height(100.dp).width(100.dp)
            )

            val myDropdownMenuForDate = MyDropdownMenu(
                dateSortingOrderItems,
                textFieldAttrForDate,
                boxAttr,
                dropdownMenuAttr
            )

            val nameSortingOrderItems = mutableListOf("A-Z","Z-A")
            var nameSortingOrderTextFieldValue by remember { mutableStateOf(TextFieldValue(nameSortingOrderItems[0])) }
            val textFieldAttrForName = TextFieldAttr(
                value = nameSortingOrderTextFieldValue,
                label = { Text(StringResourcesSingleton.SORT_FOR_NAME) },
                placeholder = { Text("") },
                onValueChange = { nameSortingOrderTextFieldValue = it },
                modifier = Modifier.height(60.dp).width(100.dp)
            )

            val myDropdownMenuForName = MyDropdownMenu(
                nameSortingOrderItems,
                textFieldAttrForName,
                boxAttr,
                dropdownMenuAttr
            )

            Spacer(modifier = Modifier.height(40.dp).fillMaxWidth())

            Row {
                myDropdownMenuForDate.popUp()
                myDropdownMenuForName.popUp()
            }

            Spacer(modifier = Modifier.height(40.dp).fillMaxWidth())
        }
    }
}