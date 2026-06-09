package ua.edu.chnu.kkn.labs

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Labo3_cross_platform",
    ) {
        App()
    }
}