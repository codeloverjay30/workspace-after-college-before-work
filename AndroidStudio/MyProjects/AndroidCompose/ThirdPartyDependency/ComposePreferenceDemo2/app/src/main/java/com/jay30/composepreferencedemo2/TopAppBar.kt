package com.jay30.composepreferencedemo2

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    TopAppBar(
        title = title,
        navigationIcon = {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
        },
    )
}