package com.tibame.tip101.group02.finalproject.healthhelper.util.sharedata

import android.content.Context
import android.content.Intent
import android.webkit.MimeTypeMap

fun ShareData(
    context: Context,
    text:String = "",
){
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}