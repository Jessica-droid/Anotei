package br.com.ascence.anotei.model.extension

import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.model.NoteStatus
import br.com.ascence.anotei.ui.presentation.NoteStatusPresentation

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
        iconColor = { category.getColor() }
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