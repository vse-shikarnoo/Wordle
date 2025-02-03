package kv.compose.wordle.ui.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kv.compose.wordle.data.game.CharacterState
import kv.compose.wordle.ui.theme.WordleComposeTheme
import kv.compose.wordle.ui.theme.keyBg
import kv.compose.wordle.ui.theme.keyBgAbsent
import kv.compose.wordle.ui.theme.keyBgCorrect
import kv.compose.wordle.ui.theme.keyBgPresent
import kv.compose.wordle.ui.theme.keyEvaluatedTextColor
import kv.compose.wordle.ui.theme.keyTextColor

fun CharacterState.keyBackgroundColor(): Color = when (this) {
    CharacterState.Unknown -> keyBg
    CharacterState.Correct -> keyBgCorrect
    CharacterState.Present -> keyBgPresent
    CharacterState.Absent -> keyBgAbsent
}

fun CharacterState.keyForegroundColor(): Color = when (this) {
    CharacterState.Unknown -> keyTextColor
    else -> keyEvaluatedTextColor
}

@Composable
fun KeyboardBox(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onKey: (char: Char) -> Unit = {},
    onBackspace: () -> Unit = {},
    onEnter: () -> Unit = {},
    keysList: List<List<Char>>
) {


    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            KeyboardRow(keys = keysList[0], onKey = {
                onKey(it)
            }, enabled = enabled)
            KeyboardRow(keys = keysList[1], onKey = {
                onKey(it)
            }, enabled = enabled)
            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                KeyboardElementSpecial(
                    string = "ENTER",
                    enabled = true,
                    state = CharacterState.Unknown,
                    onClick = {
                        onEnter()
                    },
                    textStyle = MaterialTheme.typography.bodySmall
                )
                KeyboardRow(keys = keysList[2], onKey = {
                    onKey(it)
                }, enabled = enabled)
                KeyboardElementSpecial(
                    string = "⌫",
                    enabled = true,
                    state = CharacterState.Unknown,
                    onClick = {
                        onBackspace()
                    },
                    textStyle = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun KeyboardRow(
    modifier: Modifier = Modifier,
    keys: List<Char>,
    onKey: (char: Char) -> Unit = {},
    enabled: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        for (key in keys) {
            KeyboardElement(letter = key,
                state = CharacterState.Unknown,
                onClick = {
                    onKey(it)
                }, enabled = enabled
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun KeyboardElementPreview() {
    WordleComposeTheme(darkTheme = false, highContrast = false) {
        Row(
            Modifier.padding(8.dp)
        ) {
            KeyboardElement(
                letter = 'A',
                state = CharacterState.Correct
            )
            KeyboardElement(
                letter = 'B',
                state = CharacterState.Present
            )
            KeyboardElement(
                letter = 'C',
                state = CharacterState.Absent
            )
            KeyboardElement(
                letter = 'D',
                state = CharacterState.Unknown
            )
            KeyboardElementSpecial(
                string = "⌫",
                state = CharacterState.Unknown,
                textStyle = MaterialTheme.typography.bodyLarge
            )
            KeyboardElementSpecial(
                string = "ENTER",
                state = CharacterState.Unknown
            )
        }
    }
}

@Composable
fun KeyboardElement(
    modifier: Modifier = Modifier,
    letter: Char,
    enabled: Boolean = true,
    state: CharacterState,
    onClick: (char: Char) -> Unit = {}
) {

    val backgroundColor by animateColorAsState(state.keyBackgroundColor())
    val foregroundColor by animateColorAsState(state.keyForegroundColor())

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(36.dp, height = 48.dp)
            .padding(2.dp)
            .clip(MaterialTheme.shapes.small)
            .background(backgroundColor)
            .clickable(enabled) {
                onClick(letter)
            }
    ) {
        Text(
            text = letter.toString(),
            color = foregroundColor,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
    }
}

@Composable
fun KeyboardElementSpecial(
    modifier: Modifier = Modifier,
    string: String,
    enabled: Boolean = true,
    state: CharacterState,
    onClick: () -> Unit = {},
    textStyle: TextStyle = MaterialTheme.typography.bodySmall
) {

    val backgroundColor by animateColorAsState(state.keyBackgroundColor())
    val foregroundColor by animateColorAsState(state.keyForegroundColor())

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(60.dp, height = 48.dp)
            .padding(2.dp)
            .clip(MaterialTheme.shapes.small)
            .background(backgroundColor)
            .clickable(enabled) {
                onClick()
            }
    ) {
        Text(
            text = string,
            color = foregroundColor,
            style = textStyle,
            fontWeight = FontWeight.Bold,
            modifier = modifier,
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun KeyboardBoxPreview() {
    WordleComposeTheme(darkTheme = false, highContrast = false) {
        KeyboardBox(
            modifier = Modifier.padding(8.dp),
            keysList = listOf(
                listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
                listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'),
                listOf('Z', 'X', 'C', 'V', 'B', 'N', 'M')
            ),
            enabled = true,
            onKey = { },
            onBackspace = { },
            onEnter = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun KeyboardRowPreview() {
    WordleComposeTheme(darkTheme = false, highContrast = false) {
        KeyboardRow(
            modifier = Modifier.padding(8.dp),
            listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
            onKey = {}, enabled = true
        )
    }
}

