package com.ron.tabsdemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable

fun BookDetailScreen(
    navController: NavHostController,
    book: Book,
    /* 經測試下一頁呼叫viewModel()可以取得上一頁對應的ViewModel；
        如果隔太多層使用，建議仍要傳遞 */
    tabViewModel: TabViewModel = viewModel()
) {
    tabViewModel.updateTabState(false)
    Column(
        modifier = Modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = book.image), contentDescription = "book image")
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = "ISBN: ${book.isbn}")
            Text(text = "Book name: ${book.name}")
            Text(text = "Price: ${book.price}")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = stringResource(id = R.string.button_cancel))
        }
    }
}