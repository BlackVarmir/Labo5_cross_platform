package ua.edu.chnu.kkn.labs

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import ua.edu.chnu.kkn.labs.ui.screens.home.HomeScreen
import ua.edu.chnu.kkn.labs.ui.theme.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        HomeScreen()
    }
}
