package br.com.ascence.anotei.data.local.repositories

import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun getAllNotesStream(): Flow<List<Note>>

    fun getNoteStream(id: Int): Flow<Note>

    suspend fun createNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun updateCategoryRange(category: Category, idList: List<Int>)
}