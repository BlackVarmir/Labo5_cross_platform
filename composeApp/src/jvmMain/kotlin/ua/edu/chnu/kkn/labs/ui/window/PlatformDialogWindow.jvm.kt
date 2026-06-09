package ua.edu.chnu.kkn.labs.ui.window

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.rememberDialogState
import ua.edu.chnu.kkn.labs.ui.theme.AppTheme

/**
 * Desktop actual: the dialog content is hosted in a real, separate OS window
 * (DialogWindow). The user can move it independently of the main window.
 * AppTheme is re-applied so the new window inherits the app's Material theme.
 */
@Composable
actual fun PlatformDialogWindow(
    title: String,
    onCloseRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    DialogWindow(
        onCloseRequest = onCloseRequest,
        title = title,
        state = rememberDialogState(size = DpSize(460.dp, 380.dp))
    ) {
        AppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(20.dp)
                ) {
                    content()
                }
            }
        }
    }
}
