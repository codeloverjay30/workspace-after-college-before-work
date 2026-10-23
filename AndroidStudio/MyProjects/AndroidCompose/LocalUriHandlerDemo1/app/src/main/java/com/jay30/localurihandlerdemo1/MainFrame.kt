package com.jay30.localurihandlerdemo1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource

@Composable
fun MainFrame(){
    // get the default `UriHandler` instance
    val uriHandler = LocalUriHandler.current

    val uriString = "https://stackoverflow.com/questions/66801838/how-do-i-programmatically-open-an-external-url-on-a-button-click-with-jetpack-co"
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        item {
            Button(
                onClick = {
                    // open an Uri with string `uriString` as Uri
                    uriHandler.openUri(uriString)
                }
            ) {
                Text(stringResource(R.string.open_an_uri))
            }
        }
    }
}