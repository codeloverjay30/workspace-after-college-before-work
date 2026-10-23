package com.ron.ex06_2

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
import androidx.navigation.NavHostController

@Composable
fun BookDetailScreen(navController: NavHostController, book: Book) {
    Column(
        modifier = Modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = book.image), contentDescription = "book image")
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = stringResource(id = R.string.isbn_format, book.isbn))
            Text(text = stringResource(id = R.string.bookname_format, book.name))
            Text(text = stringResource(id = R.string.price_format, book.price))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = stringResource(id = R.string.button_cancel))
        }
    }
}