package com.jay30.balloondemo1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skydoves.balloon.compose.Balloon

@Composable
fun MainFrame(){
    Box(modifier = Modifier.fillMaxSize()) {
        Balloon(
            modifier = Modifier.align(Alignment.Center),
            builder = builder,
            balloonContent = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.balloon_content_text),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        ) { balloonWindow ->
            Button(
                modifier = Modifier.size(160.dp, 60.dp),
                onClick = { balloonWindow.showAlignTop() }
            ) {
                Text(text = stringResource(R.string.balloon_window_button))
            }
        }
    }
}