package ua.edu.chnu.kkn.labs.ui.window

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

/**
 * Android actual: a regular in-app dialog overlay. (Separate OS windows only
 * make sense on desktop; here the content stays inside the single app window.)
 */
@Composable
actual fun PlatformDialogWindow(
    title: String,
    onCloseRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onCloseRequest) {
        Surface(shape = MaterialTheme.shapes.large, tonalElevation = 6.dp) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                content()
            }
        }
    }
}
