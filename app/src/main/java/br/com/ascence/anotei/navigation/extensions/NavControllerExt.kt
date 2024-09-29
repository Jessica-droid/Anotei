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

    navigateWithArgs(screen, args)
}

fun <T> NavController.popBackStackWithResult(key: String, value: T) {
    popBackStack()
    currentBackStackEntry
        ?.savedStateHandle
        ?.set(key, value)
}

fun NavController.navigateWithArgs(screen: CScreens, args: List<String>) {
    val separator = "/"
    val argsPath = separator + args.joinToString(separator)
    val fullPath = screen.routePath + argsPath

    navigate(fullPath)
}