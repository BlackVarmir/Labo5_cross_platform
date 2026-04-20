package ua.edu.chnu.kkn.labs.ui.screens.chips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.chnu.kkn.labs.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipsScreen() {
    var filterSelected by remember { mutableStateOf(false) }
    var elevatedFilterSelected by remember { mutableStateOf(true) }
    var inputSelected by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AssistChip(
            onClick = {},
            label = { Text("Assist chip") },
            leadingIcon = {
                Icon(Icons.Filled.Settings, contentDescription = null)
            }
        )

        ElevatedAssistChip(
            onClick = {},
            label = { Text("Elevated assist chip") }
        )

        FilterChip(
            selected = filterSelected,
            onClick = { filterSelected = !filterSelected },
            label = { Text("Filter chip") },
            leadingIcon = if (filterSelected) {
                { Icon(Icons.Filled.Done, contentDescription = null) }
            } else null
        )

        ElevatedFilterChip(
            selected = elevatedFilterSelected,
            onClick = { elevatedFilterSelected = !elevatedFilterSelected },
            label = { Text("Elevated filter chip") }
        )

        InputChip(
            selected = inputSelected,
            onClick = { inputSelected = !inputSelected },
            label = { Text("Input chip") }
        )

        SuggestionChip(
            onClick = {},
            label = { Text("Suggestion chip") }
        )
    }
}

@Preview
@Composable
fun ChipsScreenPreview() {
    AppTheme {
        Scaffold {
            ChipsScreen()
        }
    }
}
