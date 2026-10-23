package com.jay30.insetmodifierdemo1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun MainFrame() {
    Column(Modifier.padding(16.dp).consumeWindowInsets(PaddingValues(16.dp))) {
        Text(stringResource(R.string.text1))
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.ime))
        Text(stringResource(R.string.text2))
    }
}