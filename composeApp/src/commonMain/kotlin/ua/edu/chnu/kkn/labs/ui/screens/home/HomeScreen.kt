package ua.edu.chnu.kkn.labs.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ua.edu.chnu.kkn.labs.data.timezones.TimeZoneHelperImpl
import ua.edu.chnu.kkn.labs.ui.screens.meeting.MeetingScreen
import ua.edu.chnu.kkn.labs.ui.screens.timezones.TimeZonesScreen

/**
 * Main screen of the app (requirement #1). Hosts the two pages via a bottom
 * navigation bar and owns the list of selected time zones so both pages share it:
 *  - tab 0: the time-zones page
 *  - tab 1: the meeting-time page
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val helper = remember { TimeZoneHelperImpl() }
    val selectedZones = remember { mutableStateListOf<String>() }
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (selectedTab == 0) "Time Zones" else "Meeting Time")
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Filled.DateRange, contentDescription = null) },
                    label = { Text("Time Zones") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    label = { Text("Meeting") }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            when (selectedTab) {
                0 -> TimeZonesScreen(helper = helper, selectedZones = selectedZones)
                else -> MeetingScreen(helper = helper, selectedZones = selectedZones)
            }
        }
    }
}
