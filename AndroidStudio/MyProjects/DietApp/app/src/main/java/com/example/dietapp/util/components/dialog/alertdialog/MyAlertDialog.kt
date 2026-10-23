package com.example.dietapp.util.components.dialog.alertdialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.dietapp.R
import com.example.dietapp.resources.stringresources.StringResourcesSingleton

/**
 * MyAlertDialog
 *
 * an object than can pop up an alert dialog
 */

object MyAlertDialog {
    @Composable
    fun popUp(
        onDismissRequest: () -> Unit,
        onConfirmation: () -> Unit,
        dialogTitle: String,
        dialogText: String,
    ) {
        AlertDialog(
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText)
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text(StringResourcesSingleton.CONFIRM)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text(StringResourcesSingleton.DISMISS)
                }
            }
        )
    }
}