package com.jay30.modalnavigationdrawerdemo

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ModalNavigationDrawerDemoFrame() {
    val context = LocalContext.current

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var selected by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .border(width = 3.dp, color = Color.Blue),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = selected,
                    onClick = {
                        Toast.makeText(
                            context,
                            "NavigationDrawerItem is clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                        selected = !selected
                    }
                )
            }
        }
    ) {
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Show drawer") },
                    icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                val message = if (isClosed) {
                                    "will open the ModalNavigationDrawer."
                                } else{
                                    "will close the ModalNavigationDrawer."
                                }
                                Toast.makeText(
                                    context,
                                    message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                )
            }
        ) { contentPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
            ){
                item {
                    Text("A text")
                }
            }
        }
    }
}