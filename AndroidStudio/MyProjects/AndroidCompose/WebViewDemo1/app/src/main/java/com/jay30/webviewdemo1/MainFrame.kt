package com.jay30.webviewdemo1

import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun MainFrame(){
    // Declare a string that contains a url
    val mUrl = "https://www.google.com"

    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            this.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            this.webChromeClient = CustomWebChromeClient()
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}

class CustomWebChromeClient : WebChromeClient() {
    override fun onCloseWindow(window: WebView?) {

    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        return true
    }
}