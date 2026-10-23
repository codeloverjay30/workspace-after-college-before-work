package com.jay30.composablenavigationdemo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun ProfileFrame(
    onNavigationEvent: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Button(onClick = onNavigationEvent) {
                Text(stringResource(R.string.jump_to, stringResource(MainScreenEnum.SEXY_STORE.title)))
            }
        }
    }
}