package com.jay30.movablecontentofdemo1.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember

@Composable
fun <T> List<T>.movable(
    transform: @Composable (item: T) -> Unit
): @Composable (item: T) -> Unit {
    val composedItems = remember(this) { mutableMapOf<T, @Composable () -> Unit>() }
    return { item: T ->
        composedItems.getOrPut(item) {
            movableContentOf { transform(item) }
        }.invoke()
    }
}