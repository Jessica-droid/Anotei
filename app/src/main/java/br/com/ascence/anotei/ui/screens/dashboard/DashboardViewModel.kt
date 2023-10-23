package br.com.ascence.anotei.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DashboardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DashBoardState())
    val uiState: StateFlow<DashBoardState> = _uiState.asStateFlow()

    init {
        _uiState.value = DashBoardState()
    }

    fun setupNoteOptions(note: Note) {

        val options = NoteOption.getAllOptions(
            note = note,
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