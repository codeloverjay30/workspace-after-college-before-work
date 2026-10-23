package com.jay30.layoutconstraintdemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LayoutConstraintDemoFrame() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .sizeIn(
                        minWidth = 50.dp,
                        maxWidth = 100.dp,
                        minHeight = 50.dp,
                        maxHeight = 100.dp,
                    )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
                    .size(50.dp)
            )

            Image(
                painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                Modifier
                    .clip(CircleShape)
                    .padding(10.dp)
                    .size(100.dp)
            )
        }
    }
}