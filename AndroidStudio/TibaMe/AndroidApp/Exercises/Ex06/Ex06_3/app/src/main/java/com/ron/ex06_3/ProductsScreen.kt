package com.ron.ex06_3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProductsScreen() {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(id = Screen.Phones.title),
        stringResource(id = Screen.Computers.title)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            when (tabIndex) {
                0 -> PhonesScreen()
                1 -> ComputersScreen()
            }
        }

        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = colorResource(id = R.color.teal_200)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(text = title) },
                    // 判斷此頁籤是否為選取頁籤
                    selected = index == tabIndex,
                    // 點擊此頁籤後即改為選取頁籤
                    onClick = { tabIndex = index },
                    // 設定選取顏色
                    selectedContentColor = colorResource(id = R.color.purple_700),
                    // 設定未選取顏色
                    unselectedContentColor = Color.Gray,
                    icon = {
                        when (index) {
                            0 -> Icon(
                                painter = painterResource(id = R.drawable.phone),
                                contentDescription = "phone"
                            )

                            1 -> Icon(
                                painter = painterResource(id = R.drawable.computer),
                                contentDescription = "computer"
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    ProductsScreen()
}