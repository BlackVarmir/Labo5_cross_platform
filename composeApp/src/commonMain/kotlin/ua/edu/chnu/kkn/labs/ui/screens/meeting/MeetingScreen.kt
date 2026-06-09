package ua.edu.chnu.kkn.labs.ui.screens.meeting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.edu.chnu.kkn.labs.data.timezones.TimeZoneHelper
import ua.edu.chnu.kkn.labs.ui.components.HourSelector

/**
 * Page 1.b from the lab: find a suitable meeting time across the selected zones.
 * Uses two custom [HourSelector] controls for the start and end of the interval,
 * a search button, and shows the result in [SearchResultDialog]. The search is
 * delegated to the time-zone helper class.
 */
@Composable
fun MeetingScreen(
    helper: TimeZoneHelper,
    selectedZones: SnapshotStateList<String>
) {
    var startHour by remember { mutableStateOf(9) }
    var endHour by remember { mutableStateOf(17) }
    var result by remember { mutableStateOf<List<Int>?>(null) }
    var showResult by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Find a meeting time", style = MaterialTheme.typography.titleLarge)
        Text(
            "Pick a working interval. We'll find the hours that also fall inside it " +
                "for every time zone you selected.",
            style = MaterialTheme.typography.bodyMedium
        )

        HourSelector(
            label = "Start of interval",
            hour = startHour,
            onHourChange = { startHour = it }
        )
        HourSelector(
            label = "End of interval",
            hour = endHour,
            onHourChange = { endHour = it }
        )

        Button(
            onClick = {
                result = helper.search(startHour, endHour, selectedZones.toList())
                showResult = true
            },
            enabled = selectedZones.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Filled.Search, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Search")
        }

        if (selectedZones.isEmpty()) {
            Text(
                "Add some time zones on the Time Zones tab first.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

    if (showResult && result != null) {
        SearchResultDialog(
            hours = result!!,
            startHour = startHour,
            endHour = endHour,
            onDismiss = { showResult = false }
        )
    }
}
