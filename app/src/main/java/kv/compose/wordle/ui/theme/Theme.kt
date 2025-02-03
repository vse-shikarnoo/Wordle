package kv.compose.wordle.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.accompanist.systemuicontroller.rememberSystemUiController


var isSystemInDarkTheme by mutableStateOf(false)
var isHighContrastMode by mutableStateOf(false)

private val DarkColorPalette = darkColorScheme(
    primary = accentColor,
    secondary = accentColor,
    background = colorTone7,
    surface = colorTone7,
    onPrimary = white,
    onSecondary = white,
    onBackground = colorTone1,
    onSurface = colorTone1,
)

private val LightColorPalette = lightColorScheme(
    primary = accentColor,
    secondary = accentColor,
    background = colorTone7,
    surface = colorTone7,
    onPrimary = white,
    onSecondary = white,
    onBackground = colorTone1,
    onSurface = colorTone1,
)

@Composable
fun WordleComposeTheme(
    darkTheme: Boolean,
    highContrast: Boolean,
    content: @Composable () -> Unit
) {
    isSystemInDarkTheme = darkTheme
    isHighContrastMode = highContrast

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = colorTone7)

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = shapes,
        content = content,
    )
}
