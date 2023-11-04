package br.com.ascence.anotei.model.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
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

fun Note.getOptions(
    onCategoryClick: () -> Unit,
    onScheduleClick: () -> Unit,
    onProtectClick: () -> Unit,
    onDeleteClick: () -> Unit,
): List<NoteOption> = listOf(
    NoteOption.Category(
        action = onCategoryClick,
        iconColor = { getCategoryColor() }
    ),
    NoteOption.Schedule(
        action = onScheduleClick,
        showBadge = isScheduled()
    ),
    NoteOption.Protect(
        action = onProtectClick,
        showBadge = isProtected()
    ),
    NoteOption.Delete(
        action = onDeleteClick
    ),
)

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