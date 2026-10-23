package com.jay30.inkgeometrydemo1

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.Snapshot
import androidx.ink.geometry.ImmutableBox
import androidx.ink.strokes.Stroke

fun eraseWholeStrokes(
    eraserBox: ImmutableBox,
    finishedStrokesState: MutableState<Set<Stroke>>,
) {
    val threshold = 0.1f

    val strokesToErase = finishedStrokesState.value.filter { stroke ->
        stroke.shape.computeCoverageIsGreaterThan(
            box = eraserBox,
            coverageThreshold = threshold,
        )
    }
    if (strokesToErase.isNotEmpty()) {
        Snapshot.withMutableSnapshot {
            finishedStrokesState.value -= strokesToErase
        }
    }
}