package br.com.ascence.anotei.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class ThemeColors(
    primaryTextColor: Color,
    secondaryTextColor: Color,
    secondaryBackgroundColor: Color,
    tertiaryTextColor: Color,
    accentColor: Color,
    primaryButtonTextColor: Color,
    menuColor: Color,
    allChipColor: Color,
    secondChipColor: Color,
    thirdChipColor: Color,
    fourthColor: Color,
    fifithChipColor: Color,
    lockColor: Color,
    appTitleColor: Color,
    selectedNoteColor: Color,
    statusBarColor: Color,
    bottomBarColor: Color,
    bottomBarFabColor: Color,
    colorScheme: ColorScheme,
) {
    var primaryTextColor by mutableStateOf(primaryTextColor)
        private set

    var secondaryTextColor by mutableStateOf(secondaryTextColor)
        private set

    var tertiaryTextColor by mutableStateOf(tertiaryTextColor)
        private set

    var secondaryBackgroundColor by mutableStateOf(secondaryBackgroundColor)
        private set

    var accentColor by mutableStateOf(accentColor)
        private set

    var primaryButtonTextColor by mutableStateOf(primaryButtonTextColor)
        private set

    var menuColor by mutableStateOf(menuColor)
        private set

    var allChipColor by mutableStateOf(allChipColor)
        private set

    var secondChipColor by mutableStateOf(secondChipColor)
        private set

    var thirdChipColor by mutableStateOf(thirdChipColor)
        private set


    var fourthColor by mutableStateOf(fourthColor)
        private set


    var fifithChipColor by mutableStateOf(fifithChipColor)
        private set


    var lockColor by mutableStateOf(lockColor)
        private set

    var appTitleColor by mutableStateOf(appTitleColor)
        private set

    var selectedNoteColor by mutableStateOf(selectedNoteColor)
        private set

    var statusBarColor by mutableStateOf(statusBarColor)
        private set

    var bottomBarColor by mutableStateOf(bottomBarColor)
        private set

    var bottomBarFabColor by mutableStateOf(bottomBarFabColor)
        private set

    var colorScheme by mutableStateOf(colorScheme)
        private set

    fun copy(
        primaryTextColor: Color = this.primaryTextColor,
        secondaryTextColor: Color = this.secondaryTextColor,
        secondaryBackgroundColor: Color = this.secondaryBackgroundColor,
        tertiaryTextColor: Color = this.tertiaryTextColor,
        accentColor: Color = this.accentColor,
        primaryButtonTextColor: Color = this.primaryButtonTextColor,
        menuColor: Color = this.menuColor,
        allChipColor: Color = this.allChipColor,
        secondChipColor: Color = this.secondChipColor,
        thirdChipColor: Color = this.thirdChipColor,
        fourthColor: Color = this.fourthColor,
        fifithChipColor: Color = this.fifithChipColor,
        lockColor: Color = this.lockColor,
        appTitleColor: Color = this.appTitleColor,
        selectedNoteColor: Color = this.selectedNoteColor,
        statusBarColor: Color = this.statusBarColor,
        bottomBarColor: Color = this.bottomBarColor,
        bottomBarFabColor: Color = this.bottomBarFabColor,
        colorScheme: ColorScheme = this.colorScheme,
    ) = ThemeColors(
        primaryTextColor = primaryTextColor,
        secondaryTextColor = secondaryTextColor,
        secondaryBackgroundColor = secondaryBackgroundColor,
        tertiaryTextColor = tertiaryTextColor,
        accentColor = accentColor,
        primaryButtonTextColor = primaryButtonTextColor,
        menuColor = menuColor,
        allChipColor = allChipColor,
        secondChipColor = secondChipColor,
        thirdChipColor = thirdChipColor,
        fourthColor = fourthColor,
        fifithChipColor = fifithChipColor,
        lockColor = lockColor,
        appTitleColor = appTitleColor,
        selectedNoteColor = selectedNoteColor,
        statusBarColor = statusBarColor,
        bottomBarColor = bottomBarColor,
        bottomBarFabColor = bottomBarFabColor,
        colorScheme = colorScheme
    )

    fun updateColorsFrom(other: ThemeColors) = ThemeColors(
        primaryTextColor = other.primaryTextColor,
        secondaryTextColor = other.primaryTextColor,
        secondaryBackgroundColor = other.primaryTextColor,
        tertiaryTextColor = other.tertiaryTextColor,
        accentColor = other.primaryTextColor,
        primaryButtonTextColor = other.primaryTextColor,
        menuColor = other.primaryTextColor,
        allChipColor = other.primaryTextColor,
        secondChipColor = other.primaryTextColor,
        thirdChipColor = other.primaryTextColor,
        fourthColor = other.primaryTextColor,
        fifithChipColor = other.primaryTextColor,
        lockColor = other.primaryTextColor,
        appTitleColor = other.primaryTextColor,
        selectedNoteColor = other.primaryTextColor,
        statusBarColor = other.primaryTextColor,
        bottomBarColor = other.bottomBarColor,
        bottomBarFabColor = other.bottomBarFabColor,
        colorScheme = other.colorScheme
    )
}

val LocalColors = staticCompositionLocalOf { lightThemeColors() }