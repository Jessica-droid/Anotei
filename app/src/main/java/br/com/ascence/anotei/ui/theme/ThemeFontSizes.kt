package br.com.ascence.anotei.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class ThemeFontSizes(
    val none: TextUnit = 0.sp,
    val xxxSmall: TextUnit = 2.sp,
    val xxSmall: TextUnit = 4.sp,
    val xSmall: TextUnit = 8.sp,
    val small: TextUnit = 12.sp,
    val medium: TextUnit = 16.sp,
    val large: TextUnit = 20.sp,
    val xLarge: TextUnit = 24.sp,
    val xxLarge: TextUnit = 28.sp,
    val xxxLarge: TextUnit = 32.sp,
)

val LocalFontSizes = staticCompositionLocalOf { ThemeFontSizes() }