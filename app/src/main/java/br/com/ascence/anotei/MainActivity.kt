package br.com.ascence.anotei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import br.com.ascence.anotei.navigation.SetupAppNavigation
import br.com.ascence.anotei.ui.theme.AnoteiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val controller = rememberNavController()

            AnoteiTheme {
                SetupAppNavigation(navController = controller)
            }
        }
    }
}