package kv.compose.wordle.ui.settings

data class SettingsViewState(
    val hardMode: Boolean = false,
    val darkTheme: Boolean = false,
    val highContrastMode: Boolean = false,
)