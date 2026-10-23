package com.tibame.tip101.group02.finalproject.healthhelper.components.dialog.alertdialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.tibame.tip101.group02.finalproject.healthhelper.consts.stringresourcesingleton.StringResourceSingleton

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
                    Text(StringResourceSingleton.CONFIRM_OPTION_TEXT)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text(StringResourceSingleton.DISMISS_OPTION_TEXT)
                }
            }
        )
    }
}