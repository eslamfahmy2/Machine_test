package com.example.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(

    primary = Color.Black,

    primaryVariant = Color.Black,
    secondary = Color.Black, // appbar background

    background = Color.Black,
    surface = surfaceDark, // items back
    onPrimary = Color.White, //text
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,


)

private val LightColorPalette = lightColors(

    primary = Color.Black,
    primaryVariant = Purple700,
    secondary = Color.Cyan ,// appbar background

    background = Color.White,
    surface = Color.White,  // items back
    onPrimary = Color.Black, // text
    onSecondary = text, // text gray
    onBackground = Color.Black,
    onSurface = Color.Black,


)

@Composable
fun YarabTheme(isDark: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (isDark) {
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