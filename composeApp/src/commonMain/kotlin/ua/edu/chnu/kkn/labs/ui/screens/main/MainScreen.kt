package ua.edu.chnu.kkn.labs.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import labo4_cross_platform.composeapp.generated.resources.Res
import labo4_cross_platform.composeapp.generated.resources.buttons
import labo4_cross_platform.composeapp.generated.resources.checkboxes
import labo4_cross_platform.composeapp.generated.resources.chips
import labo4_cross_platform.composeapp.generated.resources.current_date
import labo4_cross_platform.composeapp.generated.resources.current_time
import labo4_cross_platform.composeapp.generated.resources.current_timezone
import labo4_cross_platform.composeapp.generated.resources.datepicker
import labo4_cross_platform.composeapp.generated.resources.dialog
import labo4_cross_platform.composeapp.generated.resources.divider
import labo4_cross_platform.composeapp.generated.resources.progress_bar
import labo4_cross_platform.composeapp.generated.resources.radio_buttons
import labo4_cross_platform.composeapp.generated.resources.switch_component
import labo4_cross_platform.composeapp.generated.resources.timepicker
import co.touchlab.kermit.Logger
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import ua.edu.chnu.kkn.labs.data.timezones.TimeZoneHelper
import ua.edu.chnu.kkn.labs.data.timezones.TimeZoneHelperImpl

@Composable
fun MainScreen(
    onButtonsClicked: () -> Unit,
    onCheckboxesClicked: () -> Unit,
    onChipsClicked: () -> Unit,
    onDatepickerClicked: () -> Unit,
    onDialogClicked: () -> Unit,
    onDividerClicked: () -> Unit,
    onProgressBarClicked: () -> Unit,
    onRadioButtonsClicked: () -> Unit,
    onSwitchClicked: () -> Unit,
    onTimepickerClicked: () -> Unit,
) {
    val timeZoneHelper: TimeZoneHelper = remember { TimeZoneHelperImpl() }
    val currentTimeZone = remember { timeZoneHelper.currentTimeZone() }
    val currentTime = remember { timeZoneHelper.currentTime() }
    val currentDate = remember { timeZoneHelper.getDate(currentTimeZone) }

    LaunchedEffect(Unit) {
        Logger.i { "MainScreen: currentTimeZone=$currentTimeZone" }
        Logger.i { "MainScreen: currentTime=$currentTime" }
        Logger.i { "MainScreen: currentDate=$currentDate" }
    }

    val navItems: List<Pair<StringResource, () -> Unit>> = listOf(
        Res.string.buttons to onButtonsClicked,
        Res.string.checkboxes to onCheckboxesClicked,
        Res.string.chips to onChipsClicked,
        Res.string.datepicker to onDatepickerClicked,
        Res.string.dialog to onDialogClicked,
        Res.string.divider to onDividerClicked,
        Res.string.progress_bar to onProgressBarClicked,
        Res.string.radio_buttons to onRadioButtonsClicked,
        Res.string.switch_component to onSwitchClicked,
        Res.string.timepicker to onTimepickerClicked,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(Res.string.current_timezone, currentTimeZone),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(Res.string.current_date, currentDate),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(Res.string.current_time, currentTime),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        navItems.forEach { (titleRes, onClick) ->
            val label = stringResource(titleRes)
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    Logger.d { "MainScreen: $label button clicked" }
                    onClick()
                }
            ) {
                Text(label)
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        onButtonsClicked = {},
        onCheckboxesClicked = {},
        onChipsClicked = {},
        onDatepickerClicked = {},
        onDialogClicked = {},
        onDividerClicked = {},
        onProgressBarClicked = {},
        onRadioButtonsClicked = {},
        onSwitchClicked = {},
        onTimepickerClicked = {},
    )
}
