package com.example.exer4_04.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import com.example.exer4_04.R
import com.example.exer4_04.classes.Friend
import kotlinx.coroutines.launch

object FriendsInfoUI {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun show(){
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                // 建立small TopAppBar，預設為置左對齊
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = { Text(text = "friends") },
                    scrollBehavior = scrollBehavior
                )
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) { innerPadding ->
            // 一定要套用innerPadding，否則內容無法跟TopAppBar對齊
            FriendLists(fetchFriends(), innerPadding) { friend ->
                scope.launch {
                    snackbarHostState.showSnackbar(
                        "${friend.name}, ${friend.phone}",
                        withDismissAction = true
                    )
                }
            }
        }
    }

    @Composable
    fun FriendLists(
        friends: List<Friend>,
        innerPadding: PaddingValues,
        onItemClick: (Friend) -> Unit
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(friends) { friend ->
                // 用來建立Lists內容物
                ListItem(
                    modifier = Modifier.clickable {
                        onItemClick(friend)
                    },
                    headlineContent = { Text(friend.name) },
                    supportingContent = { Text(friend.phone) },
                    leadingContent = {
                        Image(
                            painter = painterResource(id = friend.image),
                            contentDescription = "friend"
                        )
                    }
                )
                HorizontalDivider()
            }
        }
    }
    /**
     * 載入測試需要資料
     * @return 多本書資訊
     */
    fun fetchFriends(): List<Friend> {
        return listOf(
            Friend("0001", "Ivy", "0912343420", R.drawable.ivy),
            Friend("0002", "Mary", "0908934730", R.drawable.mary),
            Friend("0003", "Sue", "0987466644", R.drawable.sue),
        )
    }
}

