package com.example.dietapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dietapp.R
import com.example.dietapp.classes.DietDiariesSingleton

object DietDiaryUI {
    @Composable
    fun show() {
        val ADD = stringResource(R.string.add)
        val SEARCH = stringResource(R.string.search)
        val SORT = stringResource(R.string.sort)
        val DIET = stringResource(R.string.diet)
        val DIARY = stringResource(R.string.diary)
        val NAME = stringResource(R.string.name)
        val MEAL = stringResource(R.string.meal)
        val DATE = stringResource(R.string.date)
        val TIME = stringResource(R.string.time)
        val DIET_DIARY_NAME = "${DIET} ${DIARY} ${NAME}"
        val DIET_DIARY_MEAL_DATE = "${DIET} ${DIARY} ${MEAL} ${DATE}"
        val DIET_DIARY_MEAL_TIME = "${DIET} ${DIARY} ${MEAL} ${TIME}"

        var addButtonIsClicked by remember { mutableStateOf(false) }
        var searchButtonIsClicked by remember { mutableStateOf(false) }
        var sortButtonIsClicked by remember { mutableStateOf(false) }
        if (addButtonIsClicked) {
            AddDietDiaryUI.show()
        }else if(searchButtonIsClicked){
            SearchDietDiaryUI.show()
        }else if(sortButtonIsClicked){
            SortDietDiaryUI.show()
        }else{
            Column{
                Spacer(
                    modifier = Modifier.height(40.dp).fillMaxWidth()
                )
                Row{
                    Button(
                        onClick = { addButtonIsClicked = true }
                    ) {
                        Text(ADD)
                    }
                    Button(
                        onClick = { searchButtonIsClicked = true }
                    ) {
                        Text(SEARCH)
                    }
                    Button(
                        onClick = { sortButtonIsClicked = true }
                    ) {
                        Text(SORT)
                    }
                }
                for (dietary in DietDiariesSingleton.dietDiaries) {
                    Spacer(
                        modifier = Modifier.height(40.dp).fillMaxWidth()
                    )
                    Card(
                        modifier = Modifier.height(100.dp).fillMaxWidth()
                    ) {
                        Text(
                            "${DIET_DIARY_NAME}: ${dietary.name}",
                            modifier = Modifier.height(20.dp).fillMaxWidth()
                        )
                        Spacer(
                            modifier = Modifier.height(20.dp).fillMaxWidth()
                        )
                        Text(
                            "${DIET_DIARY_MEAL_DATE} ${dietary.mealDate}",
                            modifier = Modifier.height(20.dp).fillMaxWidth()
                        )
                        Spacer(
                            modifier = Modifier.height(20.dp).fillMaxWidth()
                        )
                        Text(
                            "${DIET_DIARY_MEAL_TIME} ${dietary.mealTime}",
                            modifier = Modifier.height(20.dp).fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}