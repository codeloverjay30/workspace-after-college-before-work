package com.jay30.textdemo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextDemoFrame(){
    LazyColumn(
        modifier = Modifier
            .size(400.dp,1500.dp)
            .border(width = 3.dp,color = Color.Blue)
    ){
        val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))

        item{
            Text(stringResource(R.string.normal_string))
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            // example of argument color in Text,
            Text(text = stringResource(R.string.green_string), color = Color.Green)
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            // example of argument textAlign in Text,
            Text(text = stringResource(R.string.align_center_string), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            // example of argument textDecoration in Text,
            Text(text = stringResource(R.string.underlined_string), textDecoration = TextDecoration.Underline)
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            // example of argument letterSpacing in Text,
            Text(text = stringResource(R.string.letter_spacing_10_sp_string), letterSpacing = 10.sp)
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            // example of argument lineHeight in Text,
            Text(text = stringResource(R.string.line_height_10_sp_string), lineHeight = 10.sp)
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            // example of argument overflow in Text,
            Text(text = stringResource(R.string.text_overflow_clip_string), overflow = TextOverflow.Clip)
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            // example of argument overflow in Text,
            Text(text = stringResource(R.string.text_overflow_visible_string), overflow = TextOverflow.Visible)
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            // example of argument overflow in Text,
            Text(text = stringResource(R.string.text_overflow_ellipsis_string), overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            // example of argument fontStyle in Text,
            Text(text = stringResource(R.string.font_style_italic_string), fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            // example of argument softWrap in Text,
            Text(text = stringResource(R.string.no_soft_wrap_string), softWrap = false)
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            // example of argument minLines in Text,
            Text(text = stringResource(R.string.min_2_lines_string), color = Color.Cyan,minLines = 2)
            Text("-".repeat(40))

            // example of argument maxLines in Text,
            Text(text = stringResource(R.string.min_2_lines_and_max_3_lines_string), color = Color.Blue,minLines = 2, maxLines = 3)
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            // example of argument  Modifier.graphicsLayer and  Modifier.drawWithCache in Text,
            // thanks to author of [`Brushing up on Compose Text coloring`](https://medium.com/androiddevelopers/brushing-up-on-compose-text-coloring-84d7d70dd8fa)
            Text(
                text = "Text in Compose ❤️",
                modifier = Modifier.graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(brush, blendMode = BlendMode.SrcAtop)
                        }
                    }
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextDemoFramePreview(){
    MaterialTheme{
        TextDemoFrame()
    }
}