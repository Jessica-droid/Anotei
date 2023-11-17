package br.com.ascence.anotei.ui.screens.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ascence.anotei.data.local.extension.toNotesList
import br.com.ascence.anotei.data.local.repositories.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesListViewModel(
    private val notesRepository: NotesRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotesListState())
    val uiState: StateFlow<NotesListState> = _uiState.asStateFlow()

    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            notesRepository.getAllNotesStream().collect { entities ->
                _uiState.update { currentState ->
                    currentState.copy(
                        notes = entities.toNotesList()
                    )
                }
            }
        }
    }
}