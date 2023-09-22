package br.com.ascence.anotei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.ascence.anotei.ui.screens.dashboard.Dashboard
import br.com.ascence.anotei.ui.theme.AnoteiTheme
import br.com.ascence.anotei.ui.theme.darkThemeColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnoteiTheme(darkColors = darkThemeColors()) {
                Dashboard()
            }
        }
    }
}