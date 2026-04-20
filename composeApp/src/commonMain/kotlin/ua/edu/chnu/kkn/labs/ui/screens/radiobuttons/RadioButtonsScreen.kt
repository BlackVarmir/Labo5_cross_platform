package ua.edu.chnu.kkn.labs.ui.screens.radiobuttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.chnu.kkn.labs.ui.theme.AppTheme

@Composable
fun RadioButtonsScreen() {
    val options = listOf("Option A", "Option B", "Option C")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).selectableGroup(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text("Select an option:", modifier = Modifier.padding(bottom = 8.dp))

        options.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (option == selectedOption),
                        onClick = { selectedOption = option },
                        role = Role.RadioButton
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = null
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Text(
            text = "Selected: $selectedOption",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview
@Composable
fun RadioButtonsScreenPreview() {
    AppTheme {
        Scaffold {
            RadioButtonsScreen()
        }
    }
}
