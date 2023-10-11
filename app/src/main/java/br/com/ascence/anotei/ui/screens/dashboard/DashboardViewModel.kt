package br.com.ascence.anotei.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.model.NoteOption.Category
import br.com.ascence.anotei.model.NoteOption.Delete
import br.com.ascence.anotei.model.NoteOption.Protect
import br.com.ascence.anotei.model.NoteOption.Schedule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DashBoardState())
    val uiState: StateFlow<DashBoardState> = _uiState.asStateFlow()

    init {
        _uiState.value = DashBoardState(noteOptions = setupNoteOptions())
    }

    private fun setupNoteOptions(): List<NoteOption> = listOf(
        Category(
            action = {} // TODO setup category change method
        ),
        Schedule(
            action = {} // TODO setup category change method
        ),
        Protect(
            action = {} // TODO setup category change method
        ),
        Delete(
            action = {} // TODO setup category change method
        ),
    )
}