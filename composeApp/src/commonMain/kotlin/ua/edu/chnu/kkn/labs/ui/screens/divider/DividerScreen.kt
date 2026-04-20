package ua.edu.chnu.kkn.labs.ui.screens.divider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.chnu.kkn.labs.ui.theme.AppTheme

@Composable
fun DividerScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Section 1", style = MaterialTheme.typography.titleMedium)
        Text("Content of the first section above the divider.")

        HorizontalDivider()

        Text("Section 2", style = MaterialTheme.typography.titleMedium)
        Text("Content of the second section below the divider.")

        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primary
        )

        Text("Section 3", style = MaterialTheme.typography.titleMedium)

        Row(
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) {
            Text("Left", modifier = Modifier.padding(end = 8.dp))
            VerticalDivider()
            Text("Right", modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Preview
@Composable
fun DividerScreenPreview() {
    AppTheme {
        Scaffold {
            DividerScreen()
        }
    }
}
