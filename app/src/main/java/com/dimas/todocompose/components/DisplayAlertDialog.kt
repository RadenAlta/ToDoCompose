package com.dimas.todocompose.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dimas.todocompose.R

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClicked: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
            },
            confirmButton = {
                Button(onClick = {
                    onYesClicked()
                    closeDialog()
                }) {
                    Text(text = stringResource(R.string.yes))
                }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    closeDialog()
                }) {
                    Text(text = stringResource(R.string.no))
                }
            },
            onDismissRequest = { closeDialog() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayAlertDialogPreview() {
    DisplayAlertDialog(
        title = "Hapus Data?",
        message = "Apakah Anda yakin ingin menghapus data ini?",
        openDialog = true,
        closeDialog = {},
        onYesClicked = {}
    )
}
