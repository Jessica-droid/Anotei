package br.com.ascence.anotei.model.extension

import br.com.ascence.anotei.data.local.entities.NoteEntity
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteStatus
import br.com.ascence.anotei.ui.presentation.NoteStatusPresentation
import kotlin.time.Duration.Companion.milliseconds

fun Note.toStatusPresentation(): List<NoteStatusPresentation> =
    status.map { noteStatus ->
        when (noteStatus) {
            NoteStatus.PROTECTED -> NoteStatusPresentation.PROTECTED
            NoteStatus.SCHEDULED -> NoteStatusPresentation.SCHEDULED
        }
    }

fun Note.toEntity(): NoteEntity =
    when (this) {
        is Note.TextNote -> NoteEntity(
            id = id,
            type = NoteEntity.NoteEntityType.TEXT,
            title = title,
            description = description,
            category = category.toCategoryEntity(),
            status = mutableListOf(),
            creationDateInMillis = creationDate.time.milliseconds.inWholeMilliseconds
        )

        is Note.TodoList -> TODO()
    }