package kv.compose.wordle.ui.settings

import kv.compose.wordle.ui.base.BaseViewModel

class SettingsViewModel(
    initialState: SettingsViewState,
) : BaseViewModel<SettingsViewState>(
    initialState = initialState,
) {

    fun setDarkTheme(isDarkTheme: Boolean) {
        updateState {
            copy(
                darkTheme = isDarkTheme,
            )
        }
    }

    fun setHardMode(enabled: Boolean) {
        updateState {
            copy(
                hardMode = enabled,
            )
        }
    }

    fun setHighContrastMode(enabled: Boolean) {
        updateState {
            copy(
                highContrastMode = enabled,
            )
        }
    }
}