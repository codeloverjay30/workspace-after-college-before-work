package com.jay30.uitestingdemo1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jay30.uitestingdemo1.ui.theme.UiTestingDemo1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiTestingDemo1Theme {
            }
        }
    }
}

@Preview(widthDp = 50, heightDp = 50)
@Composable
fun SquareComposablePreview() {
    Box(Modifier.background(Color.Yellow)) {
        Text("Hello World")
    }
}

@Preview(device = "id:pixel_5")
@Composable
fun DifferentDeviceComposablePreview(){
    Box(Modifier.background(Color.Yellow)) {
        Text("Hello World")
    }
}

@Preview(locale = "fr-rFR")
@Composable
fun DifferentLocaleComposablePreview() {
    Text(text = stringResource(R.string.greeting))
}

@Preview(showSystemUi = true)
@Composable
fun DecoratedComposablePreview() {
    Text("Hello World")
}

@Preview(uiMode = Configuration.UI_MODE_TYPE_CAR)
@Composable
fun DifferentUIModeComposablePreview() {
    Text("Hello World")
}

@Composable
fun GreetingScreen(name: String) {
    if (LocalInspectionMode.current) {
        // Show this text in a preview window:
        Text("Hello preview user!")
    } else {
        // Show this text in the app:
        Text("Hello $name!")
    }
}

@Preview(
    name = "small font",
    group = "font scales",
    fontScale = 0.5f
)
@Preview(
    name = "large font",
    group = "font scales",
    fontScale = 1.5f
)
annotation class FontScalePreviews

@FontScalePreviews
@Composable
fun HelloWorldPreview() {
    Text("Hello World")
}

@Preview(
    name = "Spanish",
    group = "locale",
    locale = "es"
)
@FontScalePreviews
annotation class CombinedPreviews

@CombinedPreviews
@Composable
fun HelloWorldPreview2() {
    MaterialTheme { Surface { Text(stringResource(R.string.hello_world)) } }
}
