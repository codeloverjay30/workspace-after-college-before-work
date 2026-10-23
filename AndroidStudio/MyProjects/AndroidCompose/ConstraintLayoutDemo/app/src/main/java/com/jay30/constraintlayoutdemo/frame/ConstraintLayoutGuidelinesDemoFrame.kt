package com.jay30.constraintlayoutdemo.frame

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ConstraintLayoutGuidelinesDemoFrame(){
    ConstraintLayout {
        // Create guideline from the start of the parent at 10% the width of the Composable
        val startGuideline = createGuidelineFromStart(0.1f)
        // Create guideline from the end of the parent at 10% the width of the Composable
        val endGuideline = createGuidelineFromEnd(0.1f)
        //  Create guideline from 16 dp from the top of the parent
        val topGuideline = createGuidelineFromTop(16.dp)
        //  Create guideline from 16 dp from the bottom of the parent
        val bottomGuideline = createGuidelineFromBottom(16.dp)

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ){
            item{
                Box(
                    modifier = Modifier
                        .size(200.dp,200.dp)
                        .border(width = 3.dp,color = Color.Blue)
                        .constrainAs(
                            ref = startGuideline.reference as ConstrainedLayoutReference,
                            constrainBlock = {
                                clearConstraints()
                            }
                        )
                ){
                }

                Box(
                    modifier = Modifier
                        .size(200.dp,200.dp)
                        .border(width = 3.dp,color = Color.Blue)
                        .constrainAs(
                            ref = endGuideline.reference as ConstrainedLayoutReference,
                            constrainBlock = {
                                clearConstraints()
                            }
                        )
                ){
                }
            }
        }
    }
}