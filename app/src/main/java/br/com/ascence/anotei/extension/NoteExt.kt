package br.com.ascence.anotei.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteStatus
import br.com.ascence.anotei.ui.presentation.NoteStatusPresentation
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

fun Note.toStatusPresentation(): List<NoteStatusPresentation> =
    status.map { noteStatus ->
        when (noteStatus) {
            NoteStatus.PROTECTED -> NoteStatusPresentation.PROTECTED
            NoteStatus.SCHEDULED -> NoteStatusPresentation.SCHEDULED
        }
    }

@Composable
fun Note.getCategoryColor(): Color =
    with(AnoteiAppTheme.colors) {
        when (category) {
            Category.DEFAULT -> allChipColor
            Category.PURPLE -> secondChipColor
            Category.PINK -> thirdChipColor
            Category.RED -> fourthColor
            Category.GREEN -> fifithChipColor
        }
    }

@Composable
fun Note.getProtectionIconColor(): Color =
    with(AnoteiAppTheme.colors) {
        if (isProtected) lockColor else menuColor
    }

@Composable
fun Note.getSchedulerIconColor(): Color =
    with(AnoteiAppTheme.colors) {
        if (isScheduled) lockColor  else menuColor
    }