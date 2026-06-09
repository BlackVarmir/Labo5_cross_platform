package ua.edu.chnu.kkn.labs.ui.screens.meeting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import ua.edu.chnu.kkn.labs.ui.components.formatHour

/**
 * Dialog #3 from the lab: shows the result of the meeting-time search produced by
 * the time-zone helper class.
 */
@Composable
fun SearchResultDialog(
    hours: List<Int>,
    startHour: Int,
    endHour: Int,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = { Icon(Icons.Filled.Info, contentDescription = null) },
        title = { Text("Search result") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Interval: " + formatHour(startHour) + " – " + formatHour(endHour),
                    style = MaterialTheme.typography.bodyMedium
                )
                if (hours.isEmpty()) {
                    Text("No suitable hour fits all of your selected time zones.")
                } else {
                    Text("Hours that work for everyone (your local time):")
                    Text(
                        hours.joinToString(", ") { formatHour(it) },
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("OK") }
        }
    )
}
