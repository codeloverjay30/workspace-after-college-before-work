package com.jay30.composableeffectsdemo

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jay30.composableeffectsdemo.repository.DefaultColorRepository

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = {},
        actions = {},
        colors = DefaultColorRepository.myTopAppBarColors
    )
}