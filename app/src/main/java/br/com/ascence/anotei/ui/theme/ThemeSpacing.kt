package br.com.ascence.anotei.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ThemeSpacing(
    val none: Dp = 0.dp,
    val xxxSmall: Dp = 2.dp,
    val xxSmall: Dp = 4.dp,
    val xSmall: Dp = 8.dp,
    val small: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 20.dp,
    val xLarge: Dp = 24.dp,
    val xxLarge: Dp = 28.dp,
    val xxxLarge: Dp = 32.dp,
)

val LocalSpacing = staticCompositionLocalOf { ThemeSpacing() }