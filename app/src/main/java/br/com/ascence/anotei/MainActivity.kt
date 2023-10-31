package br.com.ascence.anotei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.ascence.anotei.navigation.DASHBOARD_PATH
import br.com.ascence.anotei.navigation.NOTE_PATH
import br.com.ascence.anotei.ui.screens.dashboard.Dashboard
import br.com.ascence.anotei.ui.screens.note.NoteScreen
import br.com.ascence.anotei.ui.theme.AnoteiTheme
import br.com.ascence.anotei.ui.theme.darkThemeColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val slideEffect = spring<IntOffset>(dampingRatio = Spring.DampingRatioMediumBouncy)

            val controller = rememberNavController()

            AnoteiTheme(darkColors = darkThemeColors()) {
                NavHost(navController = controller, startDestination = DASHBOARD_PATH) {
                    composable(DASHBOARD_PATH) { Dashboard(navController = controller) }
                    composable(
                        route = NOTE_PATH,
                        enterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { 1000 },
                                animationSpec = slideEffect
                            )
                        },
                        exitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { -1000 },
                                animationSpec = slideEffect
                            )
                        },
                        popEnterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { -1000 },
                                animationSpec = slideEffect
                            )
                        },
                        popExitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { 1000 },
                                animationSpec = slideEffect
                            )
                        }
                    ) { NoteScreen(navController = controller) }
                }
            }
        }
    }
}