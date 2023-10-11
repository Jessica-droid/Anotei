package br.com.ascence.anotei.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import br.com.ascence.anotei.model.NoteOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DashBoardState())
    val uiState: StateFlow<DashBoardState> = _uiState.asStateFlow()

    init {
        _uiState.value = DashBoardState(noteOptions = setupNoteOptions())
    }

    private fun setupNoteOptions(): List<NoteOption> = NoteOption.getAllOptions(
        onCategoryClick = {}, // TODO setup category selection
        onScheduleClick = {}, // TODO setup scheduling
        onProtectClick = {}, // TODO setup note protection
        onDeleteClick = {} // TODO setup note deletion
    )
}