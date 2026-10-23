package com.jay30.localtexttoolbardemo1

import android.view.View
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.platform.TextToolbarStatus

class CustomTextToolbar(
    override val status: TextToolbarStatus,
    private val view : View,
) : TextToolbar {
    override fun hide() {
        TODO("Not yet implemented")
    }

    override fun showMenu(
        rect: Rect,
        onCopyRequested: (() -> Unit)?,
        onPasteRequested: (() -> Unit)?,
        onCutRequested: (() -> Unit)?,
        onSelectAllRequested: (() -> Unit)?
    ) {
        println("showMenu")
        view.startActionMode(TextActionModeCallback())
    }
}