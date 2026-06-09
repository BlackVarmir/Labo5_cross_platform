package ua.edu.chnu.kkn.labs.ui.screens.meeting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.edu.chnu.kkn.labs.ui.components.formatHour
import ua.edu.chnu.kkn.labs.ui.window.PlatformDialogWindow

/**
 * Dialog #3 from the lab: shows the result of the meeting-time search.
 *
 * Lab 5 (requirement 2.a): the dialog is now hosted by [PlatformDialogWindow], so
 * on desktop it opens in a separate OS window while staying inline elsewhere.
 * The "Search result" heading is provided by [PlatformDialogWindow] itself.
 */
@Composable
fun SearchResultDialog(
    hours: List<Int>,
    startHour: Int,
    endHour: Int,
    onDismiss: () -> Unit
) {
    PlatformDialogWindow(title = "Search result", onCloseRequest = onDismiss) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Interval: " + formatHour(startHour) + " - " + formatHour(endHour),
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismiss) { Text("OK") }
            }
        }
    }
}
