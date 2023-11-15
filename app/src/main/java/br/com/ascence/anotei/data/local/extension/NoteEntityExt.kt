package br.com.ascence.anotei.data.local.extension

import br.com.ascence.anotei.data.local.entities.NoteEntity
import br.com.ascence.anotei.data.local.entities.NoteEntity.NoteEntityType
import br.com.ascence.anotei.model.Note
import java.util.Date

fun NoteEntity.toNote(): Note =
    when (this.type) {
        NoteEntityType.TEXT -> Note.SimpleText(
            id = id,
            title = title,
            status = status,
            category = category,
            creationDate = Date(creationDateInMillis),
            description = description.orEmpty()
        )

        NoteEntityType.TEXT_LIST -> Note.TodoList(
            id = id,
            title = title,
            status = status,
            category = category,
            creationDate = Date(creationDateInMillis),
            items = itemsList ?: emptyList()
        )
    }

fun List<NoteEntity>.toNotesList(): List<Note> = map { entity ->
    entity.toNote()
}