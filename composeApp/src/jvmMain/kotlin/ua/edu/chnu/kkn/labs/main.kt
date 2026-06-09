package ua.edu.chnu.kkn.labs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

/**
 * Desktop entry point (Lab 5, requirement 2.b and 2.c).
 *
 *  - Multi-window support: [openWindows] holds the ids of all currently open
 *    windows. Opening a new window adds an id; closing one removes it. When the
 *    last window closes the application exits.
 *  - Menu bar + keyboard shortcuts: each window has a File / View menu with
 *    accelerators (New Window = Ctrl+N, Close Window = Ctrl+W, Exit = Ctrl+Q).
 */
fun main() = application {
    // Ids of the windows that are currently open. Start with a single window (id 0).
    val openWindows = remember { mutableStateListOf(0) }
    // Monotonic counter so every new window gets a unique, stable id.
    var nextWindowId by remember { mutableStateOf(1) }

    openWindows.forEach { windowId ->
        key(windowId) {
            val windowState = rememberWindowState(size = DpSize(420.dp, 820.dp))
            Window(
                onCloseRequest = {
                    openWindows.remove(windowId)
                    if (openWindows.isEmpty()) exitApplication()
                },
                state = windowState,
                title = "Time Zones - Window ${windowId + 1}"
            ) {
                MenuBar {
                    Menu(text = "File", mnemonic = 'F') {
                        Item(
                            text = "New Window",
                            shortcut = KeyShortcut(Key.N, ctrl = true)
                        ) {
                            openWindows.add(nextWindowId++)
                        }
                        Item(
                            text = "Close Window",
                            shortcut = KeyShortcut(Key.W, ctrl = true)
                        ) {
                            openWindows.remove(windowId)
                            if (openWindows.isEmpty()) exitApplication()
                        }
                        Separator()
                        Item(
                            text = "Exit",
                            shortcut = KeyShortcut(Key.Q, ctrl = true)
                        ) {
                            exitApplication()
                        }
                    }
                    Menu(text = "View", mnemonic = 'V') {
                        Item(
                            text = "Open another window",
                            shortcut = KeyShortcut(Key.T, ctrl = true)
                        ) {
                            openWindows.add(nextWindowId++)
                        }
                    }
                }
                App()
            }
        }
    }
}
