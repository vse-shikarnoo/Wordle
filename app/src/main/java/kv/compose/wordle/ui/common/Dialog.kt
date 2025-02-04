package kv.compose.wordle.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kv.compose.wordle.ui.theme.colorTone7
import kv.compose.wordle.ui.theme.isSystemInDarkTheme
import kv.compose.wordle.ui.theme.keyTextColor

@Preview
@Composable
fun DialogPreviewLightMode() {
    isSystemInDarkTheme = false

    Dialog(visible = true, title = "Settings", onClose = {}, content = {})
}

@Preview
@Composable
fun DialogPreviewDarkMode() {
    isSystemInDarkTheme = true

    Dialog(visible = true, title = "Settings", onClose = {}, content = {})
}

@Composable
fun Dialog(
    visible: Boolean,
    title: String,
    onClose: () -> Unit,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorTone7)
                .padding(8.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = title.uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = keyTextColor,
                    )
                }

                Box(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {

                    IconButton(
                        modifier = Modifier.size(30.dp),
                        onClick = onClose,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Close",
                            tint = keyTextColor,
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                content()
            }
        }
    }
}