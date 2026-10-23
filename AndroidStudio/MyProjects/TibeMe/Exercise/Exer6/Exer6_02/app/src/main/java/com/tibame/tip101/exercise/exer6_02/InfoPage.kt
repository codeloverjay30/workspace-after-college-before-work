package com.tibame.tip101.exercise.exer6_02

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun InfoPage(
    navController: NavHostController,
    book: Book,
) {
    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = book.image),
            contentDescription = book.name,
        )
        Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
        Text("ISBN: ${book.isbn}")
        Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
        Text("Book name: ${book.name}")
        Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
        Text("Price: ${book.price}")
        Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
        Button(
            onClick = { navController.popBackStack() }
        ) {
            Text(text = stringResource(id = R.string.button_cancel))
        }

    }
}