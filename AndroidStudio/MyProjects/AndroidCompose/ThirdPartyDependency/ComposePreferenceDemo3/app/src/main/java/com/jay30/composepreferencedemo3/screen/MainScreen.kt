package com.jay30.composepreferencedemo2.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jay30.composepreferencedemo3.enumclass.MainScreenEnum
import com.jay30.composepreferencedemo3.frame.AccountListSettingFrame
import com.jay30.composepreferencedemo3.frame.AccountSettingFrame
import com.jay30.composepreferencedemo3.frame.AppSettingFrame
import com.jay30.composepreferencedemo3.frame.CallVibrationSettingFrame
import com.jay30.composepreferencedemo3.frame.ConnectionSettingFrame
import com.jay30.composepreferencedemo3.frame.MainFrame
import com.jay30.composepreferencedemo3.frame.RingtoneSettingFrame
import com.jay30.composepreferencedemo3.frame.SimSettingFrame
import com.jay30.composepreferencedemo3.frame.SoundsAndVibrationSettingFrame
import com.jay30.composepreferencedemo3.frame.VolumeSettingFrame

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreenEnum.MAIN_FRAME.name
    ) {
        composable(
            route = MainScreenEnum.MAIN_FRAME.name
        ){
            MainFrame(
                navController = navController
            )
        }

        composable(
            route = MainScreenEnum.CONNECTION_SETTING_FRAME.name
        ){
            ConnectionSettingFrame(
                navController = navController
            )
        }

        composable(
            route = MainScreenEnum.SIM_SETTING_FRAME.name
        ){
            SimSettingFrame(
                navController = navController
            )
        }

        composable(
            route = MainScreenEnum.SOUNDS_AND_VIBRATION_SETTING_FRAME.name
        ){
            SoundsAndVibrationSettingFrame(
                navController = navController
            )
        }

        composable(
            route = MainScreenEnum.CALL_VIBRATION_SETTING_FRAME.name
        ){
            CallVibrationSettingFrame(
                navController = navController
            )
        }

        composable(
            route = MainScreenEnum.RINGTONE_SETTING_FRAME.name
        ){
            RingtoneSettingFrame(
                navController = navController
            )
        }

        composable(
            route = MainScreenEnum.VOLUME_SETTING_FRAME.name
        ){
            VolumeSettingFrame(
                navController = navController
            )
        }

        composable(
            route = MainScreenEnum.APP_SETTING_FRAME.name
        ){
            AppSettingFrame(
                navController = navController
            )
        }

        composable(
            route = MainScreenEnum.ACCOUNT_LIST_SETTING_FRAME.name
        ){
            AccountListSettingFrame(
                navController = navController
            )
        }

        composable(
            route = MainScreenEnum.ACCOUNT_SETTING_FRAME.name
        ){
            AccountSettingFrame(
                navController = navController
            )
        }
    }
}