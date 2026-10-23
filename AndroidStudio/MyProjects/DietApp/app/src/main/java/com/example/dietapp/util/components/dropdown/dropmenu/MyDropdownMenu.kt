package com.example.dietapp.util.components.dropdown.dropmenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import com.example.dietapp.componentsattr.box.BoxAttr
import com.example.dietapp.componentsattr.dropdownmenu.DropdownMenuAttr
import com.example.dietapp.componentsattr.textfield.TextFieldAttr

/**
 * MyDropdownMenu
 *
 * a class that can pop up these components (about DropdownMenu):
 * + an OutlinedTextField
 * + a DropdownMenu
 * + a list of DropdownMenuItem under the DropdownMenu component
 */

class MyDropdownMenu(
    private val _items: MutableList<String>,
    private val _textFieldAttr: TextFieldAttr,
    private val _boxAttr: BoxAttr,
    private val _dropdownMenuAttr: DropdownMenuAttr,
){
    val items:MutableList<String> = _items
    val textFieldAttr: TextFieldAttr = _textFieldAttr
    val boxAttr: BoxAttr = _boxAttr
    val dropdownMenuAttr: DropdownMenuAttr = _dropdownMenuAttr

    @Composable
    fun popUp() {
        val keyboardOptions = KeyboardOptions.Default.copy(
            hintLocales = LocaleList(Locale("us"))
        )

        var expanded by remember { mutableStateOf(true) }
        OutlinedTextField(
            value = textFieldAttr.value,
            onValueChange = textFieldAttr.onValueChange ,
            label = textFieldAttr.label,
            readOnly = true,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            modifier = textFieldAttr.modifier.clickable {  }.onFocusChanged {
                if (it.isFocused){
                    expanded = !expanded
                }
            }
        )
        Box(
            modifier = boxAttr.modifier
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = dropdownMenuAttr.onDismissRequest,
                modifier = dropdownMenuAttr.modifier
            ) {
                for (item in items) {
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            textFieldAttr.value = TextFieldValue(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}