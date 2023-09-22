package br.com.ascence.anotei.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object AnoteiAppTheme {
    val colors: ThemeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = Typography

    val spaces: ThemeSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

    val fontSizes: ThemeFontSizes
        @Composable
        @ReadOnlyComposable
        get() = LocalFontSizes.current
}