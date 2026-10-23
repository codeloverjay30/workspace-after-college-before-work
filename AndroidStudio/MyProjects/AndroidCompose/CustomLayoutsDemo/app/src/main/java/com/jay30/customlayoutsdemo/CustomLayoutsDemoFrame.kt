package com.jay30.customlayoutsdemo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jay30.customlayoutsdemo.ext.firstBaselineToTop

@Composable
fun CustomLayoutsDemoFrame() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        item {
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(width = 3.dp,color = Color.Blue),
            ){
                item {
                    Text(stringResource(R.string.h1))
                    Text(stringResource(R.string.h2), modifier = Modifier.firstBaselineToTop(10.dp))
                }
            }
            MyBasicColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(width = 3.dp,color = Color.Red),
            ){
                Text(stringResource(R.string.h1))
                Text(stringResource(R.string.h2), modifier = Modifier.firstBaselineToTop(10.dp))
            }
        }
    }
}