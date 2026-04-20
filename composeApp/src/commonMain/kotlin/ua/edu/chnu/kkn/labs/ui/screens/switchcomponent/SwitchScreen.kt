package ua.edu.chnu.kkn.labs.ui.screens.switchcomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.chnu.kkn.labs.ui.theme.AppTheme

@Composable
fun SwitchScreen() {
    var basicChecked by remember { mutableStateOf(true) }
    var iconChecked by remember { mutableStateOf(false) }
    var disabledChecked by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Basic switch")
            Switch(
                checked = basicChecked,
                onCheckedChange = { basicChecked = it }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Switch with icons")
            Switch(
                checked = iconChecked,
                onCheckedChange = { iconChecked = it },
                thumbContent = {
                    Icon(
                        imageVector = if (iconChecked) Icons.Filled.Check else Icons.Filled.Close,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Disabled switch")
            Switch(
                checked = disabledChecked,
                onCheckedChange = { disabledChecked = it },
                enabled = false
            )
        }

        Text("Basic switch state: ${if (basicChecked) "ON" else "OFF"}")
    }
}

@Preview
@Composable
fun SwitchScreenPreview() {
    AppTheme {
        Scaffold {
            SwitchScreen()
        }
    }
}
