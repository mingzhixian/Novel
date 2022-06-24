package edu.rui.novel.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = textDark,
    secondary = textSecondDark,
    background = appBackDark,
    onBackground = itemBackDark,
    secondaryVariant = highLight,
)

private val LightColorPalette = lightColors(
    primary = textLight,
    secondary = textSecondLight,
    background = appBackLight,
    onBackground = itemBackLight,
    secondaryVariant = highLight,
)

@Composable
fun NovelTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}