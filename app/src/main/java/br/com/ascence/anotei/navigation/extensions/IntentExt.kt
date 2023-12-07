package br.com.ascence.anotei.navigation.extensions

import android.content.Intent

fun Intent.putEnumExtra(key: String, enum: Enum<*>) {
    this.putExtra(key, enum.name)
}

inline fun <reified T : Enum<T>> Intent.getEnumExtra(key: String, defaultValue: T): T =
    getStringExtra(key)?.let { enumName -> enumValueOf<T>(enumName) } ?: defaultValue