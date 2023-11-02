package br.com.ascence.anotei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.ascence.anotei.navigation.DASHBOARD_PATH
import br.com.ascence.anotei.navigation.NOTE_PATH
import br.com.ascence.anotei.ui.screens.dashboard.DashboardScreen
import br.com.ascence.anotei.ui.screens.note.NoteScreen
import br.com.ascence.anotei.ui.theme.AnoteiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val controller = rememberNavController()

            AnoteiTheme {
                NavHost(navController = controller, startDestination = DASHBOARD_PATH) {
                    composable(DASHBOARD_PATH) { DashboardScreen() }
                    composable(
                        route = NOTE_PATH,
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                animationSpec = tween(500)
                            )
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                animationSpec = tween(500)
                            )
                        },
                        popEnterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                animationSpec = tween(500)
                            )
                        },
                        popExitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                animationSpec = tween(500)
                            )
                        }
                    ) { NoteScreen(navController = controller) }
                }
            }
        }
    }
}