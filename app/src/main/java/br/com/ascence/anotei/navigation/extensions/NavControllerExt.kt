package br.com.ascence.anotei.navigation.extensions

import androidx.navigation.NavController

fun <T> NavController.navigateForResultOnce(key: String, onResult: (T) -> Unit) {
    val screenResult = currentBackStackEntry
        ?.savedStateHandle
        ?.get<T>(key)

    screenResult?.let {
        onResult(it)

        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(key)
    }
}

fun <T> NavController.popBackStackWithResult(key: String, value: T) {
    currentBackStackEntry
        ?.savedStateHandle
        ?.set(key, value)

    popBackStack()
}