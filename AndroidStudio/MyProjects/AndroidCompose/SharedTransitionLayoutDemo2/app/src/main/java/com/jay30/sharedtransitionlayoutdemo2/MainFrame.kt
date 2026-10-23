package com.jay30.sharedtransitionlayoutdemo2

import android.graphics.Rect
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainFrame(){
    SharedTransitionLayout {
        var isExpanded by remember { mutableStateOf(false) }

        AnimatedContent(targetState = isExpanded, label = "") { target ->
            if (!target) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp)
                        .clickable {
                            isExpanded = !isExpanded
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "image"),
                                animatedVisibilityScope = this@AnimatedContent,
                            )
                            .size(130.dp),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "name"),
                                animatedVisibilityScope = this@AnimatedContent,
                            )
                            .fillMaxWidth()
                            .padding(12.dp),
                        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                        fontSize = 12.sp,
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            isExpanded = !isExpanded
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "image"),
                                animatedVisibilityScope = this@AnimatedContent,
                            )
                            .fillMaxWidth()
                            .height(320.dp),
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "name"),
                                animatedVisibilityScope = this@AnimatedContent,
                            )
                            .fillMaxWidth()
                            .padding(21.dp),
                        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }
}