package br.com.ascence.anotei.model.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

@Composable
fun Category.getColor(): Color =
    with(AnoteiAppTheme.colors) {
        when (this@getColor) {
            Category.DEFAULT -> allChipColor
            Category.PURPLE -> secondChipColor
            Category.PINK -> thirdChipColor
            Category.RED -> fourthColor
            Category.GREEN -> fifithChipColor
        }
    }