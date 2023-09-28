package br.com.ascence.anotei.ui.screens.notes

import androidx.lifecycle.ViewModel
import br.com.ascence.anotei.data.mock.notesListMock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotesListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NotesListState())
    val uiState: StateFlow<NotesListState> = _uiState.asStateFlow()

    init {
        _uiState.value = NotesListState(notes = notesListMock)
    }
}