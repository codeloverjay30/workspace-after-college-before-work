package com.example.dietapp.ui
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.dietapp.R
import com.example.dietapp.classes.DietDiariesSingleton
import com.example.dietapp.classes.DietDiary
import com.example.dietapp.classes.Food
import com.example.dietapp.componentsattr.box.BoxAttr
import com.example.dietapp.componentsattr.dropdownmenu.DropdownMenuAttr
import com.example.dietapp.componentsattr.textfield.TextFieldAttr
import com.example.dietapp.resources.stringresources.StringResourcesSingleton
import com.example.dietapp.util.components.dialog.alertdialog.MyAlertDialog
import com.example.dietapp.util.components.picker.datepicker.MyDatePickerDialog
import com.example.dietapp.util.components.dropdown.dropmenu.MyDropdownMenu
import com.example.dietapp.util.components.picker.timepicker.MyTimePickerDialog
import com.example.dietapp.util.validator.Validator

object AddDietDiaryUI {

    @SuppressLint("SimpleDateFormat")
    @Composable
    fun show() {
        var dietDiaryName by remember { mutableStateOf("") }
        val dietDiaryDateButtonText by remember { mutableStateOf(StringResourcesSingleton.OPEN_DATE_PICKER_DIALOG) }
        val dietDiaryTimeButtonText by remember { mutableStateOf(StringResourcesSingleton.OPEN_TIME_PICKER_DIALOG) }
        var addButtonIsClicked by remember { mutableStateOf(false) }
        var onConfirmationIsClicked by remember { mutableStateOf(false) }
        var onDismissRequestIsClicked by remember { mutableStateOf(false) }

        val foodItems = listOf(Food("apple"),Food("banana"))
        var selectedItemTextFieldValue by remember {  mutableStateOf(TextFieldValue(foodItems[0].name)) }
        val dateSelectedText = remember { mutableStateOf("") }
        val timeSelectedText = remember { mutableStateOf("") }

        if(onConfirmationIsClicked || onDismissRequestIsClicked){
            addButtonIsClicked = false
            AddDietDiaryUI.show()
        }
        if (addButtonIsClicked) {
            if(Validator.isValid(
                    dateSelectedText.value.toString(),
                    timeSelectedText.value.toString(),
                    dietDiaryName)
                ){
                val lastId = DietDiariesSingleton.dietDiaries.lastOrNull()?.id ?: 0
                val dietDiary = DietDiary(
                    lastId + 1,
                    dietDiaryName,
                    dateSelectedText.value,
                            dateSelectedText.value
                )
                DietDiariesSingleton.dietDiaries.add(dietDiary)
                DietDiaryUI.show()
            }else{
                MyAlertDialog.popUp(
                    dialogTitle = StringResourcesSingleton.ERROR,
                    dialogText = StringResourcesSingleton.NOT_VALID_DATE_OR_TIME_OR_STRING,
                    onConfirmation = {onConfirmationIsClicked = true},
                    onDismissRequest = {onDismissRequestIsClicked = true},
                    )
            }
        }else{
            addButtonIsClicked = false
            onConfirmationIsClicked  = false
            onDismissRequestIsClicked = false
            Column(
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(
                    modifier = Modifier.height(20.dp).fillMaxWidth()
                )

                Button(
                    onClick = {addButtonIsClicked=true},
                ) {
                    //Text(StringResourcesSingleton.ADD)
                    Icon(
                        painter = painterResource(R.drawable.plus),
                        contentDescription = "add",
                        modifier = Modifier.height(20.dp).width(20.dp)
                    )
                }
                Spacer(
                    modifier = Modifier.height(40.dp).fillMaxWidth()
                )

                TextField(
                    value = dietDiaryName,
                    onValueChange = { dietDiaryName = it },
                    label = { Text(StringResourcesSingleton.DIET_DIARY_NAME) },
                    singleLine = true
                )

                val textFieldAttr = TextFieldAttr(
                     value = selectedItemTextFieldValue,
                    onValueChange = {selectedItemTextFieldValue = it},
                    label = {Text(StringResourcesSingleton.SELECT_FOOD)},
                    placeholder = { Text("") }
                )

                val boxAttr = BoxAttr(
                    modifier = Modifier.fillMaxWidth()
                )

                val dropdownMenuAttr = DropdownMenuAttr(
                    onDismissRequest = {},
                    modifier = Modifier.wrapContentSize()
                )

                val myDropdownMenu = MyDropdownMenu(
                    foodItems.map{it.name}.toMutableList(),
                    textFieldAttr,
                    boxAttr,
                    dropdownMenuAttr
                )

                myDropdownMenu.popUp()

                Spacer(modifier = Modifier.height(40.dp).fillMaxWidth())

                val myDatePickerDialog = MyDatePickerDialog(
                    buttonText = dietDiaryDateButtonText,
                    modifier = Modifier.height(40.dp).width(200.dp),
                    onDateSelected = {dateSelectedText.value = it }
                )

                Text(dateSelectedText.value.toString())
                Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
                myDatePickerDialog.popUp()

                val myTimePickerDialog = MyTimePickerDialog(
                    buttonText = dietDiaryTimeButtonText,
                    boxModifier = Modifier.height(40.dp).fillMaxWidth(),
                    timeSelectedText = timeSelectedText
                )
                Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
                Text(timeSelectedText.value.toString())
                Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
                myTimePickerDialog.popUp()

                Spacer(modifier = Modifier.height(60.dp).fillMaxWidth())
            }
        }
    }
}