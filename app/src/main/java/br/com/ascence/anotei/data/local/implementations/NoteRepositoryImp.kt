package br.com.ascence.anotei.data.local.implementations

import br.com.ascence.anotei.data.local.dao.NoteDao
import br.com.ascence.anotei.data.local.entities.NoteEntity
import br.com.ascence.anotei.data.local.extension.toNotesList
import br.com.ascence.anotei.data.local.repositories.NotesRepository
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.extension.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImp(private val noteDao: NoteDao) : NotesRepository {
    override suspend fun getAllNotesStream(): Flow<List<Note>> =
        noteDao.getAllNotes().map { notes -> notes.toNotesList() }

    override fun getNoteStream(id: Int): Flow<NoteEntity> = noteDao.getNote(noteId = id)

    override suspend fun createNote(note: Note) = noteDao.create(note.toEntity())

    override suspend fun deleteNote(note: NoteEntity) = noteDao.delete(note)

    override suspend fun updateNote(note: Note) = noteDao.update(note.toEntity())
}