package br.com.ascence.anotei.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.ascence.anotei.ui.screens.dashboard.DashboardScreen
import br.com.ascence.anotei.ui.screens.noteDisplay.NoteDisplay

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SetupAppNavigation(navController: NavHostController, sTScope: SharedTransitionScope) {
    NavHost(navController = navController, startDestination = CScreens.DASHBOARD.routePath) {
        composable(CScreens.DASHBOARD.routePath) {
            sTScope.DashboardScreen(
                navController = navController,
                this
            )
        }
        composable(
            "${CScreens.NOTE_DISPLAY.routePath}/{$NOTE_ID_ARG}",
            arguments = listOf(
                navArgument(NOTE_ID_ARG) { type = NavType.StringType }
            )
        ) { backStepEntry ->
            sTScope.NoteDisplay(noteId = backStepEntry.arguments?.getString(NOTE_ID_ARG), this)
        }
    }
}