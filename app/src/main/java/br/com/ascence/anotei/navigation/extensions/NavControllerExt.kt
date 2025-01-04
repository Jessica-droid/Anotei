package br.com.ascence.anotei.navigation.extensions

import androidx.navigation.NavController
import br.com.ascence.anotei.navigation.CScreens

fun <T> NavController.navigateForResultOnce(
    screen: CScreens,
    args: List<String>,
    key: String,
    onResult: (T) -> Unit,
) {
    val screenResult = currentBackStackEntry
        ?.savedStateHandle
        ?.get<T>(key)

    screenResult?.let {
        onResult(it)

        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(key)
    }

    navigateToScreen(screen, args)
}

fun <T> NavController.popBackStackWithResult(key: String, value: T) {
    popBackStack()
    currentBackStackEntry
        ?.savedStateHandle
        ?.set(key, value)
}

fun NavController.navigateToScreen(screen: CScreens, args: List<String> = listOf()) {

    val fullPath = if (args.isNotEmpty()) {
        val separator = "/"
        val argsPath = separator + args.joinToString(separator)
        screen.routePath + argsPath
    } else {
        screen.routePath
    }

    navigate(fullPath)
}