package com.jay30.annotationdemo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jay30.annotationdemo2.ui.theme.AnnotationDemo2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnnotationDemo2Theme {
                DeprecationLevelWarningFunction()
            }
        }
    }
}

@Deprecated(
    message = "A @Deprecated method or function with level DeprecationLevel.HIDDEN.",
    level = DeprecationLevel.HIDDEN,
)
fun DeprecationLevelHiddenFunction(){

}

@Deprecated(
    message = "A @Deprecated method or function with level DeprecationLevel.WARNING.",
    level = DeprecationLevel.WARNING,
)
fun DeprecationLevelWarningFunction(){

}

@Deprecated(
    message = "A @Deprecated method or function with level DeprecationLevel.ERROR.",
    level = DeprecationLevel.ERROR,
)
fun DeprecationLevelErrorFunction(){

}