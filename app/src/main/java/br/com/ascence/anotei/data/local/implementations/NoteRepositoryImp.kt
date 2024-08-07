package br.com.ascence.anotei.data.local.implementations

import br.com.ascence.anotei.data.local.dao.NoteDao
import br.com.ascence.anotei.data.local.extension.toNote
import br.com.ascence.anotei.data.local.extension.toNotesList
import br.com.ascence.anotei.data.local.repositories.NotesRepository
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.extension.toCategoryEntity
import br.com.ascence.anotei.model.extension.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImp(private val noteDao: NoteDao) : NotesRepository {

    override suspend fun getAllNotesStream(): Flow<List<Note>> =
        noteDao.getAllNotes().map { notes -> notes.toNotesList() }

    override fun getNoteStream(id: Int): Flow<Note> = noteDao.getNote(noteId = id).map { entity ->
        entity.toNote()
    }

    override suspend fun createNote(note: Note) {
        noteDao.create(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note.toEntity())
    }

    override suspend fun updateNote(note: Note) {
        noteDao.update(note.toEntity())
    }

    override suspend fun updateCategoryRange(
        category: Category,
        idList: List<Int>,
    ) {
        noteDao.updateCategoryRange(
            category = category.toCategoryEntity(),
            notesIds = idList
        )
    }

    override suspend fun deleteNoteRange(idList: List<Int>) {
        noteDao.deleteNoteRange(notesIds = idList)
    }
}