package com.tibame.exercise.exercise8.exer8_01

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PicturePreviewScreen(imageUri: Uri?, onAcceptClick: () -> Unit, onRejectClick: () -> Unit) {
    val context = LocalContext.current

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var shouldShowBottomSheet by remember { mutableStateOf(true) }
    Scaffold {
        innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = onAcceptClick) {
                    Text(stringResource(id = R.string.accept))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    // 點擊"Reject"按鈕就刪除拍好的照片
                    imageUri?.let { context.contentResolver.delete(it, null, null) }
                    onRejectClick()
                }) {
                    Text(stringResource(id = R.string.reject))
                }
            }
        }
        if (shouldShowBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    shouldShowBottomSheet = false
                },
                sheetState = sheetState,
                containerColor = colorResource(id = R.color.teal_200),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                ) {
                    Text(text = imageUri.toString(), fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context)
                                .data(imageUri)
                                .size(Size.ORIGINAL)
                                .build()
                        ),
                        contentDescription = "Captured Image",
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth(0.5f)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Button(
                        modifier = Modifier.height(40.dp),
                        onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                shouldShowBottomSheet = false
                            }
                        }
                    }) {
                        Text(text = "OK", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}