package kv.compose.wordle.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import kv.compose.wordle.ui.common.SettingsListItem
import kv.compose.wordle.ui.common.SettingsSwitch
import kv.compose.wordle.ui.theme.colorTone2
import kv.compose.wordle.ui.theme.colorTone4

@Composable
fun SettingsScreen(
    state: SettingsViewState,
    viewModel: SettingsViewModel,
) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SettingsListItem(
            label = "Hard Mode",
            hint = "Any revealed hints must be used in subsequent guesses",
            content = {
                SettingsSwitch(
                    checked = state.hardMode,
                    onCheckedChange = { value ->
                        viewModel.setHardMode(enabled = value)
                    },
                )
            }
        )

        Divider(color = colorTone4)

        SettingsListItem(
            label = "Dark Theme",
            content = {
                SettingsSwitch(
                    checked = state.darkTheme,
                    onCheckedChange = { value ->
                        viewModel.setDarkTheme(isDarkTheme = value)
                    }
                )
            }
        )

        Divider(color = colorTone4)

        SettingsListItem(
            label = "High Contrast Mode",
            hint = "For improved color vision",
            content = {
                SettingsSwitch(
                    checked = state.highContrastMode,
                    onCheckedChange = { value ->
                        viewModel.setHighContrastMode(enabled = value)
                    }
                )
            }
        )
    }
}