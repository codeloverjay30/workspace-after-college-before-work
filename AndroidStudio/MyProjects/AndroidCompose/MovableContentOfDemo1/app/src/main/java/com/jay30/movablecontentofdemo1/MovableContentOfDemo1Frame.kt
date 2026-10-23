package com.jay30.movablecontentofdemo1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jay30.movablecontentofdemo1.extension.movable

@Composable
fun MovableContentOfDemo1Frame() {
    ExampleWithoutMoveContentOf()
    //ExampleWithMoveContentOf()
}


@Composable
fun ExampleWithoutMoveContentOf() {
    val counterTextList = remember {
        mutableStateListOf<String>().apply {
            for (i in 0..20) add("Counter ${'A' + i}")
        }
    }

    val counterList = remember {
        mutableStateListOf<Int>().apply {
            for (i in 0..20) add(0)
        }
    }

    Column {
        Row {
            Button(onClick = {
                counterTextList.removeFirstOrNull()


            }) {
                Text(text = "Remove first")
            }
        }
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .weight(1f)
        ) {
            counterTextList.forEachIndexed { index, it ->
                Row(
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(it)
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Button(
                            onClick = {
                                counterList[index]--
                            }
                        ) {
                            Text("-")
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(counterList[index].toString())
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(
                            onClick = {
                                counterList[index]++
                            }
                        ) {
                            Text("+")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExampleWithMoveContentOf() {
    val counterTextList = remember {
        mutableStateListOf<String>().apply {
            for (i in 0..20) add("Counter ${'A' + i}")
        }
    }

    val listComposables = counterTextList.movable {
        Text(text = it)
    }

    val counterList = remember {
        mutableStateListOf<Int>().apply {
            for (i in 0..20) add(0)
        }
    }

    Column {
        Row {
            Button(onClick = {
                counterTextList.removeFirstOrNull()


            }) {
                Text(text = "Remove first")
            }
        }
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .weight(1f)
        ) {
            counterTextList.forEachIndexed { index, it ->
                Row(
                    modifier = Modifier.fillMaxSize()
                ){
                    listComposables(it)
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Button(
                            onClick = {
                                counterList[index]--
                            }
                        ) {
                            Text("-")
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(counterList[index].toString())
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(
                            onClick = {
                                counterList[index]++
                            }
                        ) {
                            Text("+")
                        }
                    }
                }
            }
        }
    }
}