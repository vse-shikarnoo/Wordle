package kv.compose.wordle.ui.game

import kv.compose.wordle.data.game.Keyboard
import kv.compose.wordle.data.game.WordGuess
import kv.compose.wordle.data.game.WordleGame
import java.util.LinkedList
import java.util.Queue

data class WordleGameViewState(
    val game: WordleGame = WordleGame(
        targetWord = "hello",
        dictionary = listOf(),
        guesses = listOf(),
        userInput = "",
    ),
    val grid: List<WordGuess> = emptyList(),
    val keyboard: Keyboard = Keyboard(),
    val areActionsEnabled: Boolean = true,
    val userPrompts: Queue<String> = LinkedList(),
    val requestedDialogRequest: DialogRequest? = null
)

enum class DialogRequest {
    Settings,
    Help,
}