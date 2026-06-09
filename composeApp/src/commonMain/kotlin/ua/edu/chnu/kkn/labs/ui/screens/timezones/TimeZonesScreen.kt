package ua.edu.chnu.kkn.labs.ui.screens.timezones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.edu.chnu.kkn.labs.data.timezones.TimeZoneHelper

/**
 * Page 1.a from the lab: the time-zones page. Shows a card with the user's own
 * time-zone info, the list of selected time zones, and a floating action button
 * that opens the time-zone selection dialog. All data comes from [TimeZoneHelper].
 */
@Composable
fun TimeZonesScreen(
    helper: TimeZoneHelper,
    selectedZones: SnapshotStateList<String>
) {
    var showDialog by remember { mutableStateOf(false) }
    val allZones = remember { helper.getTimeZoneStrings() }
    val currentZone = remember { helper.currentTimeZone() }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("Your time zone", style = MaterialTheme.typography.labelMedium)
                    Text(currentZone, style = MaterialTheme.typography.titleLarge)
                    Text(helper.getDate(currentZone), style = MaterialTheme.typography.bodyMedium)
                    Text(helper.currentTime(), style = MaterialTheme.typography.headlineSmall)
                }
            }

            Text("Selected time zones", style = MaterialTheme.typography.titleMedium)

            if (selectedZones.isEmpty()) {
                Text(
                    "No time zones selected yet. Tap + to add one.",
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                selectedZones.forEach { zone ->
                    SelectedZoneCard(
                        helper = helper,
                        zone = zone,
                        onRemove = { selectedZones.remove(zone) }
                    )
                }
            }

            // Leave room so the FAB does not cover the last card.
            Box(modifier = Modifier.height(72.dp))
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add time zone")
        }
    }

    if (showDialog) {
        AddTimeZoneDialog(
            allZones = allZones,
            alreadySelected = selectedZones,
            onDismiss = { showDialog = false },
            onSelect = { zone ->
                if (zone !in selectedZones) selectedZones.add(zone)
                showDialog = false
            }
        )
    }
}

@Composable
private fun SelectedZoneCard(
    helper: TimeZoneHelper,
    zone: String,
    onRemove: () -> Unit
) {
    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(zone, style = MaterialTheme.typography.titleMedium)
                Text(
                    helper.getDate(zone) + " · " + helper.getTime(zone),
                    style = MaterialTheme.typography.bodyMedium
                )
                val diff = helper.hoursFromTimeZone(zone)
                Text(
                    "Difference from you: " + diff.toString() + " h",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            IconButton(onClick = onRemove) {
                Icon(Icons.Filled.Delete, contentDescription = "Remove")
            }
        }
    }
}
