package com.tibame.tip101.group02.finalproject.healthhelper.components.bar.appbar.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController

import com.tibame.tip101.group02.finalproject.healthhelper.consts.stringresourcesingleton.StringResourceSingleton
import com.tibame.tip101.group02.finalproject.healthhelper.util.sharedata.ShareData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DietAppTopBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
){
    val currentContext = LocalContext.current
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = StringResourceSingleton.DIET_APP_TITLE,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = {
            IconButton(onClick = {

            }){
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = StringResourceSingleton.HAMBURGER_ICON,
                )
            }
        },

        actions = {
            IconButton(onClick = {
                //navController.navigate(ScreenEnum.SharableAppFrame.name)
                ShareData(currentContext)
            }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = StringResourceSingleton.SHARE_ICON,
                )
            }
        },

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        scrollBehavior = scrollBehavior,
    )
}

