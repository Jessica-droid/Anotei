package br.com.ascence.anotei.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType
import br.com.ascence.anotei.ui.screens.dashboard.DashboardScreen
import br.com.ascence.anotei.ui.screens.note.NoteScreenContent

@Composable
fun SetupAppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = CScreens.DASHBOARD.routePath) {
        composable(
            route = CScreens.DASHBOARD.routePath
        ) { backStepEntry ->
            DashboardScreen(
                navBackStackEntry = backStepEntry,
                navController = navController
            )
        }
        composable(
            route = "${CScreens.NOTE_DISPLAY.routePath}/{$NOTE_ID_ARG}/{$NOTE_TYPE_ARG}",
            arguments = listOf(
                navArgument(NOTE_ID_ARG) { type = NavType.StringType },
                navArgument(NOTE_TYPE_ARG) { type = NavType.StringType }
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
            val noteTypeArg = backStepEntry.arguments?.getString(NOTE_TYPE_ARG)
            val noteType = NoteType.entries.firstOrNull {
                it.name == noteTypeArg
            } ?: NoteType.DISPLAY_NOTE
            NoteScreenContent(
                navController = navController,
                noteType = noteType,
                noteId = backStepEntry.arguments?.getString(NOTE_ID_ARG)
            )
        }
        composable(
            route = "${CScreens.NOTE_DISPLAY.routePath}/{$NOTE_TYPE_ARG}",
            arguments = listOf(
                navArgument(NOTE_TYPE_ARG) { type = NavType.StringType }
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
            val noteTypeArg = backStepEntry.arguments?.getString(NOTE_TYPE_ARG)
            val noteType = NoteType.entries.firstOrNull {
                it.name == noteTypeArg
            } ?: NoteType.NEW_NOTE
            NoteScreenContent(
                navController = navController,
                noteType = noteType,
                noteId = null
            )
        }
    }
}