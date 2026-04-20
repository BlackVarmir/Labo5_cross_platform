package ua.edu.chnu.kkn.labs.ui.screens.datepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import ua.edu.chnu.kkn.labs.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatepickerScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<String?>(null) }
    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(onClick = { showDialog = true }) {
            Text("Open date picker")
        }

        Text(
            text = selectedDate?.let { "Selected: $it" } ?: "No date selected"
        )

        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        val millis = datePickerState.selectedDateMillis
                        selectedDate = millis?.let {
                            val date = Instant.fromEpochMilliseconds(it)
                                .toLocalDateTime(TimeZone.UTC).date
                            date.toString()
                        }
                        showDialog = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

@Preview
@Composable
fun DatepickerScreenPreview() {
    AppTheme {
        Scaffold {
            DatepickerScreen()
        }
    }
}
