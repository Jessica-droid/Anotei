package br.com.ascence.anotei.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.ascence.anotei.ui.common.FeatureScreen

fun NavGraphBuilder.importFeatureScreen(
    route: String,
    screen: FeatureScreen,
    navController: NavHostController,
    arguments: List<NamedNavArgument> = emptyList(),
    useTransitionAnimation: Boolean = true,
) =
    composable(
        route = route,
        arguments = arguments,
        enterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up, tween(500)
            ).takeIf { useTransitionAnimation }
        },
        popExitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down, tween(500)
            ).takeIf { useTransitionAnimation }
        }
    ) { navBackStackEntry ->
        screen.ScreenContent(
            navBackStackEntry = navBackStackEntry,
            navController = navController
        )
    }
//TODO fazer o setup desse método agora em todas as screens