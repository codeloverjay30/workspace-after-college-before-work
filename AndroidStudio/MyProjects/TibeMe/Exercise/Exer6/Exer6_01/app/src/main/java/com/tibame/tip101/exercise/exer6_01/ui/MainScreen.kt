package com.tibame.tip101.exercise.exer6_01.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tibame.tip101.exercise.exer6_01.R
import com.tibame.tip101.exercise.exer6_01.enumclass.ScreenEnum
import com.tibame.tip101.exercise.exer6_01.ui.bmi.BmiResultScreen
import com.tibame.tip101.exercise.exer6_01.ui.bmi.BmiScreen
import com.tibame.tip101.exercise.exer6_01.ui.theme.Exer6_01_v2Theme

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Exer6_01_v2Theme {
        MainScreen.show()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    currentScreen: ScreenEnum,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}

object MainScreen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun show(
        navController: NavHostController = rememberNavController(),
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentScreen = ScreenEnum.valueOf(
            backStackEntry?.destination?.route?.split("/")?.first() ?: ScreenEnum.BMI_SCREEN.name
        )
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MainAppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = ScreenEnum.BMI_SCREEN.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)

            ) {
                composable(route = ScreenEnum.BMI_SCREEN.name) {
                    BmiScreen(
                        navController = navController
                    )
                }

                composable(
                    route = "${ScreenEnum.BMI_RESULT_SCREEN.name}/{heightText}/{weightText}"
                ) { backStackEntry ->
                    BmiResultScreen(
                        navController = navController,
                        heightText = backStackEntry.arguments?.getString("heightText") ?: "",
                        weightText = backStackEntry.arguments?.getString("weightText") ?: "",
                    )

                }
            }
        }
    }
}