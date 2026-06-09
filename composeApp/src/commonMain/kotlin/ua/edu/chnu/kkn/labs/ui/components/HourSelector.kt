package ua.edu.chnu.kkn.labs.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/** Formats an hour of day (0..23) as a "HH:00" string without relying on String.format. */
fun formatHour(hour: Int): String {
    val normalized = ((hour % 24) + 24) % 24
    val prefix = if (normalized < 10) "0" else ""
    return "$prefix$normalized:00"
}

/**
 * Custom interval-bound selector: a stepper that lets the user pick an hour of the
 * day (0..23) with wrap-around. Used for choosing the start and end of the meeting
 * search interval on the Meeting page.
 */
@Composable
fun HourSelector(
    label: String,
    hour: Int,
    onHourChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = label, style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledTonalIconButton(
                    onClick = { onHourChange(((hour - 1) + 24) % 24) },
                    modifier = Modifier.size(48.dp)
                ) {
                    Text(text = "−", style = MaterialTheme.typography.titleLarge)
                }
                Text(
                    text = formatHour(hour),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                FilledTonalIconButton(
                    onClick = { onHourChange((hour + 1) % 24) },
                    modifier = Modifier.size(48.dp)
                ) {
                    Text(text = "+", style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}
