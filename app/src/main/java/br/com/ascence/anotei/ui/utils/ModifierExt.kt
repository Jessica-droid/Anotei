package br.com.ascence.anotei.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
inline fun <T> Modifier.modifyIfNotNull(
    condition: T?,
    onModify: @Composable Modifier.(T) -> Modifier,
): Modifier =
    condition?.let {
        onModify(it)
    } ?: this

@Composable
fun Modifier.modifyIfTrue(
    condition: Boolean,
    onModify: @Composable Modifier.() -> Modifier,
): Modifier =
    if (condition) {
        then(onModify())
    } else {
        this
    }

@Composable
fun Modifier.modifyIfFalse(
    condition: Boolean,
    onModify: @Composable Modifier.() -> Modifier,
): Modifier =
    if (!condition) {
        then(onModify())
    } else {
        this
    }