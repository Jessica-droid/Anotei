package br.com.ascence.anotei.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.ascence.anotei.ui.screens.dashboard.DashboardScreen

@Composable
fun SetupAppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = DASHBOARD_PATH) {
        composable(DASHBOARD_PATH) { DashboardScreen() }
    }
}