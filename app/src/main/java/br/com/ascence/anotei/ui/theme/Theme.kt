package br.com.ascence.anotei.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

@Composable
fun AnoteiTheme(
    spaces: ThemeSpacing = AnoteiAppTheme.spaces,
    typography: Typography = AnoteiAppTheme.typography,
    lightColors: ThemeColors = AnoteiAppTheme.colors,
    darkColors: ThemeColors = darkThemeColors(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val currentColor = remember { if (darkTheme) darkColors else lightColors }
    val rememberedColors = remember { currentColor.copy() }.apply { updateColorsFrom(lightColors) }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = currentColor.statusBarColor.toArgb()
        }
    }

    CompositionLocalProvider(
        LocalSpacing provides spaces,
        LocalColors provides rememberedColors
    ) {
        ProvideTextStyle(value = typography.bodyMedium, content = content)
    }
}