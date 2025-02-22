package kv.compose.wordle.ui.game

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kv.compose.wordle.data.game.WordleGame
import kv.compose.wordle.ui.common.Dialog
import kv.compose.wordle.ui.common.GameHeader
import kv.compose.wordle.ui.common.ToastScreen
import kv.compose.wordle.ui.common.WordleGrid
import kv.compose.wordle.ui.common.WordleKeyboard
import kv.compose.wordle.ui.help.HowToPlayScreen
import kv.compose.wordle.ui.settings.SettingsScreen
import kv.compose.wordle.ui.settings.SettingsViewModel
import kv.compose.wordle.ui.settings.SettingsViewState
import kv.compose.wordle.ui.theme.WordleComposeTheme
import kv.compose.wordle.ui.theme.colorTone7


@Preview(showBackground = true, widthDp = 1080, heightDp = 1920)
@Composable
fun WordleGameScreenPreview() {

    WordleGameScreen(emptyList(), "")

}

@Composable
fun WordleGameScreen(wordList: List<String>, wordOfTheDay: String) {

    val isSystemDarkTheme = isSystemInDarkTheme()

    val settingsViewModel = remember {
        SettingsViewModel(
            initialState = SettingsViewState(darkTheme = isSystemDarkTheme)
        )
    }

    val viewModel = remember {
        WordleGameViewModel(
            initialState = WordleGameViewState(
                game = WordleGame(dictionary = wordList, targetWord = wordOfTheDay)
            )
        )
    }

    val state: WordleGameViewState by viewModel.flowState.collectAsState()
    val settingsState: SettingsViewState by settingsViewModel.flowState.collectAsState()

    WordleComposeTheme(
        darkTheme = settingsState.darkTheme,
        highContrast = settingsState.highContrastMode,
    ) {
        Surface(
            color = colorTone7,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                GameHeader(
                    modifier = Modifier.fillMaxWidth(),
                    onSettingsClicked = { viewModel.requestDialog(DialogRequest.Settings) },
                    onHowToPlayClicked = { viewModel.requestDialog(DialogRequest.Help) },
                )

                WordleGrid(
                    guesses = state.grid,
                )

                WordleKeyboard(
                    keyboard = state.keyboard,
                    enabled = state.areActionsEnabled,
                    onKey = { key ->
                        viewModel.onKeyPress(key)
                    },
                    onBackspace = { viewModel.onBackspace() },
                    onEnter = { viewModel.onEnter() },
                )
            }

            Dialog(
                visible = state.requestedDialogRequest == DialogRequest.Settings,
                title = "Settings",
                onClose = viewModel::closeDialog,
                content = {
                    SettingsScreen(
                        viewModel = settingsViewModel,
                        state = settingsState,
                    )
                }
            )

            Dialog(
                visible = state.requestedDialogRequest == DialogRequest.Help,
                title = "How to Play",
                onClose = viewModel::closeDialog,
                content = { HowToPlayScreen() }
            )

            PostGameScreen(viewModel = viewModel, state = state)

            ToastScreen(viewModel = viewModel, state = state)
        }
    }
}