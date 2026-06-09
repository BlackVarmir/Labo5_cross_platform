package ua.edu.chnu.kkn.labs

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsModule

/**
 * Lab 5 (web): fix time-zone support in the browser.
 *
 * kotlinx-datetime relies on the IANA time-zone database, which is not bundled
 * with the browser. Importing the @js-joda/timezone npm module (declared in
 * build.gradle.kts) loads that database so that TimeZone.of(...) and
 * TimeZone.availableZoneIds work the same way they do on desktop/Android.
 *
 * The assignment to jsJodaTz forces the bundler to keep the import.
 */
@OptIn(ExperimentalWasmJsInterop::class)
@JsModule("@js-joda/timezone")
external object JsJodaTimeZoneModule

private val jsJodaTz = JsJodaTimeZoneModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        App()
    }
}
