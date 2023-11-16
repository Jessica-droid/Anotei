package br.com.ascence.anotei.data.local.repositories

import br.com.ascence.anotei.data.local.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun getAllNotesStream(): Flow<List<NoteEntity>>

    fun getNoteStream(id: Int): Flow<NoteEntity>

    suspend fun createNote(note: NoteEntity)

    suspend fun deleteNote(note: NoteEntity)

    suspend fun updateNote(note: NoteEntity)
}