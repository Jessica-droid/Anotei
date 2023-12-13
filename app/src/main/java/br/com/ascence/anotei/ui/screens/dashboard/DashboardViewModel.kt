package br.com.ascence.anotei.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ascence.anotei.data.local.repositories.NotesRepository
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.extension.getOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val notesRepository: NotesRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashBoardState())
    val uiState: StateFlow<DashBoardState> = _uiState.asStateFlow()

    fun fetchNotes() {
        viewModelScope.launch {
            notesRepository.getAllNotesStream().collect { entities ->
                _uiState.update { currentState ->
                    currentState.copy(
                        notesList = entities
                    )
                }
            }
        }
    }

    fun setupNoteOptions(note: Note) {

        val options = note.getOptions(
            onCategoryClick = {}, // TODO setup category selection
            onScheduleClick = {}, // TODO setup scheduling
            onProtectClick = {}, // TODO setup note protection
            onDeleteClick = {} // TODO setup note deletion
        )

        _uiState.update { currentState ->
            currentState.copy(noteOptions = options)
        }
    }
}