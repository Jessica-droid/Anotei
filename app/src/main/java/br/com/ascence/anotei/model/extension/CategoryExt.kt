package br.com.ascence.anotei.model.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.ascence.anotei.data.local.entities.NoteEntity
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
            Category.YELLOW -> lockColor
        }
    }

fun Category.toCategoryEntity(): NoteEntity.NoteEntityCategory =
    NoteEntity.NoteEntityCategory.values()
        .first { entityCategory -> entityCategory.name == this.name }