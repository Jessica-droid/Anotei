package br.com.ascence.anotei.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.ascence.anotei.ui.screens.dashboard.DashboardScreen
import br.com.ascence.anotei.ui.screens.note.NoteScreen

@Composable
fun SetupAppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = DASHBOARD_PATH) {
        composable(DASHBOARD_PATH) { DashboardScreen() }
        composable(
            route = NOTE_PATH,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }
        ) { NoteScreen(navController = navController) }
    }
}