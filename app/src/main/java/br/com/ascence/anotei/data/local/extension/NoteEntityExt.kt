package br.com.ascence.anotei.data.local.extension

import br.com.ascence.anotei.data.local.entities.NoteEntity
import br.com.ascence.anotei.data.local.entities.NoteEntity.NoteEntityStatus
import br.com.ascence.anotei.data.local.entities.NoteEntity.NoteEntityType
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteStatus
import java.util.Date

fun NoteEntity.toNote(): Note =
    when (this.type) {
        NoteEntityType.TEXT -> Note.TextNote(
            id = id,
            title = title,
            status = status.toNoteStatusList(),
            category = category.toNoteCategory(),
            creationDate = Date(creationDateInMillis),
            description = description.orEmpty()
        )

        NoteEntityType.TEXT_LIST -> Note.TodoList(
            id = id,
            title = title,
            status = status.toNoteStatusList(),
            category = category.toNoteCategory(),
            creationDate = Date(creationDateInMillis),
            items = itemsList ?: emptyList()
        )
    }

fun List<NoteEntity>.toNotesList(): List<Note> = map { entity ->
    entity.toNote()
}

fun List<NoteEntityStatus>.toNoteStatusList(): List<NoteStatus> =
    map { entityStatus ->
        entityStatus.toNoteStatus()
    }

fun NoteEntityStatus.toNoteStatus(): NoteStatus =
    NoteStatus.values().first { noteStatus -> noteStatus.name == this.name }

fun NoteEntity.NoteEntityCategory.toNoteCategory(): Category =
    Category.values().first { entityCategory -> entityCategory.name == this.name }