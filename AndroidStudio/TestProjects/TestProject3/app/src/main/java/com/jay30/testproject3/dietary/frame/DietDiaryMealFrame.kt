package com.jay30.testproject3.dietary.frame

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jay30.testproject3.dietary.interaction.database.LoadFoodItemInfoTest1
import com.example.healthhelper.dietary.repository.FoodItemRepository
import com.example.healthhelper.dietary.viewmodel.MealsOptionViewModel

@Composable
fun DietDiaryMealFrame(
    navController: NavHostController = rememberNavController(),
) {
    val TAG = "tag_DietDiaryMealFrame"

    val context = LocalContext.current

    // get id of meal category that indicates the meal.
    val currentMealCategoryId = getMealCategoryId()

    // set the data in repo so that its corresponding view model can access it.
    FoodItemRepository.setSelectedMealCategoryId(currentMealCategoryId)
    // load food items about this diaryId from database and try to set it into repo -- FoodItemRepository if one can.
    LoadFoodItemInfoTest1(context)


}

@Composable
fun getMealCategoryId(
    mealsOptionViewModel: MealsOptionViewModel = viewModel(),
): Int {
    val mealsOptionVOs by mealsOptionViewModel.data.collectAsState()
    val selectedMealOption by mealsOptionViewModel.selectedData.collectAsState()
    val index = mealsOptionVOs.indexOf(selectedMealOption)
    if (index == -1) {
        return 0 + 1
    }
    return index + 1
}