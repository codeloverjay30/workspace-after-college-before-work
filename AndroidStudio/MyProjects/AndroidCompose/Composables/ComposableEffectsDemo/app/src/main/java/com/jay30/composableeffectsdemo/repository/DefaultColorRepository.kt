package com.jay30.composableeffectsdemo.repository

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.ui.graphics.Color
import com.jay30.composableeffectsdemo.ui.theme.Purple80

object DefaultColorRepository {
    @OptIn(ExperimentalMaterial3Api::class)
    val myTopAppBarColors: TopAppBarColors
        get() = TopAppBarColors(
            containerColor = Purple80,
            scrolledContainerColor = Color.Black,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black,
        )
}