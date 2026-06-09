package ua.edu.chnu.kkn.labs.ui.window

import androidx.compose.runtime.Composable

/**
 * Lab 5 (desktop, requirement 2.a): dialogs must open in SEPARATE OS windows.
 *
 * This is implemented with the Kotlin Multiplatform expect/actual mechanism:
 *  - the JVM (desktop) actual opens a real top-level [androidx.compose.ui.window.DialogWindow];
 *  - the Android / iOS / web actuals show a regular in-app Compose Dialog overlay.
 *
 * Both the meeting "Search result" dialog and the "Select a time zone" dialog
 * are routed through this composable, so on desktop they become independent
 * windows the user can move around, while staying inline on the other platforms.
 *
 * @param title    text shown in the desktop window's title bar (and as a heading
 *                 on the other platforms).
 * @param onCloseRequest called when the window/dialog is dismissed.
 * @param content  the dialog body.
 */
@Composable
expect fun PlatformDialogWindow(
    title: String,
    onCloseRequest: () -> Unit,
    content: @Composable () -> Unit
)
