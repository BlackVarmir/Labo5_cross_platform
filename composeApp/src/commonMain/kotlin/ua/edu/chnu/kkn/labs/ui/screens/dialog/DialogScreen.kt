package ua.edu.chnu.kkn.labs.ui.screens.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.chnu.kkn.labs.ui.theme.AppTheme

@Composable
fun DialogScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var lastAction by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(onClick = { showDialog = true }) {
            Text("Show dialog")
        }

        if (lastAction.isNotEmpty()) {
            Text("Last action: $lastAction")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                    lastAction = "Dismissed"
                },
                icon = { Icon(Icons.Filled.Info, contentDescription = null) },
                title = { Text("Confirmation") },
                text = { Text("Are you sure you want to proceed with this action?") },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        lastAction = "Confirmed"
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog = false
                        lastAction = "Dismissed"
                    }) {
                        Text("Dismiss")
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun DialogScreenPreview() {
    AppTheme {
        Scaffold {
            DialogScreen()
        }
    }
}
