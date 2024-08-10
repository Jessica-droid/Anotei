package br.com.ascence.anotei.ui.screens.noteDisplay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ascence.anotei.data.local.repositories.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteDisplayViewModel(
    private val notesRepository: NotesRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteDisplayState())
    val uiState: StateFlow<NoteDisplayState> = _uiState.asStateFlow()

    fun getNoteToDisplay(noteId: String) {
        viewModelScope.launch {
            notesRepository.getNoteStream(noteId.toInt()).collect { note ->
                _uiState.update { currentState ->
                    currentState.copy(
                        note = note
                    )
                }
            }
        }
    }
}