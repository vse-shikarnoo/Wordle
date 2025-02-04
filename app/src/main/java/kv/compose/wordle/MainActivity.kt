package kv.compose.wordle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kv.compose.wordle.ui.game.WordleGameScreen
import kv.compose.wordle.ui.theme.WordleComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val wordList: List<String> = assets.open("word_list.txt")
            .bufferedReader()
            .use { it.readText() }
            .split("\n")
        val wordOfTheDay = wordList.random()

        setContent {
            WordleGameScreen(wordList = wordList, wordOfTheDay = wordOfTheDay)
        }
    }
}

