package com.jay30.localtexttoolbardemo1

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem

class TextActionModeCallback(
) : ActionMode.Callback {
    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        println("onActionItemClicked $mode $item")
        return true
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        println("onActionItemClicked $mode $menu")
        return false
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        println("onActionItemClicked $mode $menu")
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        println("onActionItemClicked $mode")
    }
}