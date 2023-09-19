package br.com.ascence.anotei.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView

private val DarkColorScheme = darkColorScheme(
    primary = darkPrimary,
    secondary = darkSecondary,
    tertiary = darkTerteary,
    error = darkErrorColor,
    background = darkPrimaryBackgroundColor
)

private val LightColorScheme = lightColorScheme(
    primary = lightPrimary,
    secondary = lightSecondary,
    tertiary = lightTertiary,
    error = lightErrorColor,
    background = lightPrimaryBackgroundColor
)

@Composable
fun AnoteiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val (colorScheme, statusBarColor) = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) Pair(dynamicDarkColorScheme(context), darkStatusBarColor)
            else Pair(dynamicLightColorScheme(context), lightStatusBarColor)
        }

        darkTheme -> Pair(DarkColorScheme, darkStatusBarColor)
        else -> Pair(LightColorScheme, lightStatusBarColor)
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = statusBarColor.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}