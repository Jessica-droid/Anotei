package br.com.ascence.anotei.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.ascence.anotei.ui.screens.dashboard.DashboardScreen
import br.com.ascence.anotei.ui.screens.noteDisplay.NoteDisplay

@Composable
fun SetupAppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = CScreens.DASHBOARD.routePath) {
        composable(
            route = CScreens.DASHBOARD.routePath
        ) {
            DashboardScreen(navController = navController)
        }
        composable(
            route = "${CScreens.NOTE_DISPLAY.routePath}/{$NOTE_ID_ARG}",
            arguments = listOf(
                navArgument(NOTE_ID_ARG) { type = NavType.StringType }
            ),
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up, tween(500)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down, tween(500)
                )
            }
        ) { backStepEntry ->
            NoteDisplay(noteId = backStepEntry.arguments?.getString(NOTE_ID_ARG))
        }
    }
}