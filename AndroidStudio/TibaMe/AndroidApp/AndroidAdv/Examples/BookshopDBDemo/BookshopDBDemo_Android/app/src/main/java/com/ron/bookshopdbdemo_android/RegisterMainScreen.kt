package com.ron.bookshopdbdemo_android

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RegisterMainScreen(onRegisterSuccess: () -> Unit, onBackToLogin: () -> Unit) {
    val navController = rememberNavController()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    NavHost(navController = navController, startDestination = "RegisterScreen") {
        composable("RegisterScreen") {
            RegisterScreen(
                onRegisterSuccess = onRegisterSuccess,
                onBackToLogin = onBackToLogin,
                onTakePictureClick = { navController.navigate("CameraPreviewScreen") },
                onImagePicked = { uri: Uri? ->
                    imageUri = uri
                },
                imageUri = imageUri
            )
        }
        composable("CameraPreviewScreen") {
            CameraPreviewScreen(
                onPictureTaken = { uri: Uri? ->
                    imageUri = uri
                    navController.navigate("PicturePreviewScreen")
                },
                onCancelClick = {
                    imageUri = null
                    navController.popBackStack("RegisterScreen", false)
                }
            )
        }
        composable("PicturePreviewScreen") {
            PicturePreviewScreen(
                imageUri = imageUri,
                // 點擊"Accept"按鈕回到首頁並顯示照片
                onAcceptClick = { navController.popBackStack("RegisterScreen", false) },
                // 點擊"Reject"按鈕回前頁繼續拍攝
                onRejectClick = { navController.popBackStack() }
            )
        }
    }
}