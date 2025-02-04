package kv.compose.wordle.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kv.compose.wordle.data.game.WordleGameState
import kv.compose.wordle.ui.game.WordleGameViewModel
import kv.compose.wordle.ui.game.WordleGameViewState
import kv.compose.wordle.ui.theme.colorTone1
import kv.compose.wordle.ui.theme.colorTone7
import kv.compose.wordle.ui.theme.isSystemInDarkTheme

@Composable
fun ToastScreen(viewModel: WordleGameViewModel, state: WordleGameViewState) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        state.userPrompts.forEach { prompt ->
            AutoDismissToast(
                text = prompt,
                modifier = Modifier.padding(top = 80.dp),
                onDismiss = viewModel::removeUserPrompt,
            )
        }
    }
}

@Composable
fun AutoDismissToast(
    text: String,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(text) {
        delay(1000)
        visible = false
        onDismiss()
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Toast(text = text, modifier = modifier)
    }
}

@Preview
@Composable
fun ToastPreviewLightMode() {
    isSystemInDarkTheme = false
    Toast(text = "Not in word list")
}

@Preview
@Composable
fun ToastPreviewDarkMode() {
    isSystemInDarkTheme = true
    Toast(text = "Not in word list")
}

@Composable
fun Toast(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = colorTone1)
            .padding(8.dp),
        color = colorTone7,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
    )
}