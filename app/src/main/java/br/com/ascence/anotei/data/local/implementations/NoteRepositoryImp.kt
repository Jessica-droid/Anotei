package br.com.ascence.anotei.data.local.implementations

import br.com.ascence.anotei.data.local.dao.NoteDao
import br.com.ascence.anotei.data.local.entities.NoteEntity
import br.com.ascence.anotei.data.local.repositories.NotesRepository
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.extension.toEntity
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImp(private val noteDao: NoteDao) : NotesRepository {
    override fun getAllNotesStream(): Flow<List<NoteEntity>> = noteDao.getAllNotes()

    override fun getNoteStream(id: Int): Flow<NoteEntity> = noteDao.getNote(noteId = id)

    override suspend fun createNote(note: Note) = noteDao.create(note.toEntity())

    override suspend fun deleteNote(note: NoteEntity) = noteDao.delete(note)

    override suspend fun updateNote(note: NoteEntity) = noteDao.update(note)
}