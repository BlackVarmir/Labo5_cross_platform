package ua.edu.chnu.kkn.labs.ui.screens.progressbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ua.edu.chnu.kkn.labs.ui.theme.AppTheme

@Composable
fun ProgressBarScreen() {
    var progress by remember { mutableStateOf(0f) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (progress < 1f) {
                delay(100)
                progress = (progress + 0.02f).coerceAtMost(1f)
            }
            isRunning = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Indeterminate linear progress")
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

        Text("Determinate linear progress: ${(progress * 100).toInt()}%")
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Indeterminate circular")
            CircularProgressIndicator()
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Determinate circular")
            CircularProgressIndicator(progress = { progress })
        }

        Button(onClick = {
            progress = 0f
            isRunning = true
        }) {
            Text(if (isRunning) "Running..." else "Start progress")
        }
    }
}

@Preview
@Composable
fun ProgressBarScreenPreview() {
    AppTheme {
        Scaffold {
            ProgressBarScreen()
        }
    }
}
