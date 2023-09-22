package br.com.ascence.anotei.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme


private val darkColorScheme = darkColorScheme(
    primary = darkPrimary,
    secondary = darkSecondary,
    tertiary = darkTerteary,
    error = darkErrorColor,
    background = darkPrimaryBackgroundColor,
)

private val lightColorScheme = lightColorScheme(
    primary = lightPrimary,
    secondary = lightSecondary,
    tertiary = lightTertiary,
    error = lightErrorColor,
    background = lightPrimaryBackgroundColor,
)

fun darkThemeColors() = ThemeColors(
    primaryTextColor = darkPrimaryTextColor,
    secondaryTextColor = darkSecondaryTextColor,
    secondaryBackgroundColor = darkSecondaryBackgroundColor,
    accentColor = darkAccentColor,
    primaryButtonTextColor = darkPrimaryButtonTextColor,
    menuColor = darkMenuColor,
    allChipColor = darkAllChipColor,
    secondChipColor = darkSecondChipColor,
    thirdChipColor = darkThirdChipColor,
    fourthColor = darkFourthColor,
    fifithChipColor = darkFifithChipColor,
    lockColor = darkLockColor,
    appTitleColor = darkAppTitleColor,
    selectedNoteColor = darkSelectedNoteColor,
    statusBarColor = darkStatusBarColor,
    colorScheme = darkColorScheme
)

fun lightThemeColors() = ThemeColors(
    primaryTextColor = lightPrimaryTextColor,
    secondaryTextColor = lightSecondaryTextColor,
    secondaryBackgroundColor = lightSecondaryBackgroundColor,
    accentColor = lightAccentColor,
    primaryButtonTextColor = lightPrimaryButtonTextColor,
    menuColor = lightMenuColor,
    allChipColor = lightAllChipColor,
    secondChipColor = lightSecondChipColor,
    thirdChipColor = lightThirdChipColor,
    fourthColor = lightFourthColor,
    fifithChipColor = lightFifithChipColor,
    lockColor = lightLockColor,
    appTitleColor = lightAppTitleColor,
    selectedNoteColor = lightSelectedNoteColor,
    statusBarColor = lightStatusBarColor,
    colorScheme = lightColorScheme
)