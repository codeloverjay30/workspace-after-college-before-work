package com.tibame.tip101.group02.finalproject.healthhelper.components.textfield.outlinedtextfield

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.tibame.tip101.group02.finalproject.healthhelper.consts.stringresourcesingleton.StringResourceSingleton
import com.tibame.tip101.group02.finalproject.healthhelper.enumclass.ScreenEnum

@Composable
fun SearchTextField(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    var query by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = modifier,
        value = query,
        onValueChange = { query = it },
        label = { Text(StringResourceSingleton.SEARCH_LABEL)},
        leadingIcon = {
            IconButton(
                onClick = {

                }
            ){
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = StringResourceSingleton.SEARCH_ICON,
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    navController.navigate(ScreenEnum.SearchHintFrame.name)
                }
            ){
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = StringResourceSingleton.INFO_ICON,
                )
            }
        }
    )
}