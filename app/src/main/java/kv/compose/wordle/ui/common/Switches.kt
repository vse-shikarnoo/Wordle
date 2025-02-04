package kv.compose.wordle.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import kv.compose.wordle.ui.theme.accentColor
import kv.compose.wordle.ui.theme.colorTone4
import kv.compose.wordle.ui.theme.keyTextColor


@Composable
fun SettingsSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(
        checked = checked,
        onCheckedChange = { value ->
            onCheckedChange(value)
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = accentColor,
            uncheckedThumbColor = colorTone4,
            checkedTrackColor = accentColor,
            uncheckedTrackColor = colorTone4,
        )
    )
}

@Composable
fun SettingsListItem(
    label: String,
    hint: String = "",
    content: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = keyTextColor,
                fontWeight = FontWeight.Bold,
            )
        }

        content()
    }

    if (hint.isNotBlank()) {
        Text(
            text = hint,
            style = MaterialTheme.typography.titleMedium,
            color = keyTextColor,
        )
    }
}