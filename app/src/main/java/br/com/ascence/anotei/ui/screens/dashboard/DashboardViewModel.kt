package br.com.ascence.anotei.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ascence.anotei.data.local.repositories.NotesRepository
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.extension.getOptions
import br.com.ascence.anotei.model.extension.toEntity
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
                        notesList = entities,
                        showNoteOptions = false,
                        selectedNote = null,
                        showCategoryPopup = false
                    )
                }
            }
        }
    }

    fun updateNoteSelection(note: Note?) {
        _uiState.update { currentState ->
            currentState.copy(selectedNote = note)
        }
        note?.let { setupNoteOptions() }
    }

    fun updateSelectedNoteCategory(category: Category) {
        _uiState.update { currentState ->
            currentState.copy(selectedNote = currentState.selectedNote?.apply {
                this.category = category
            })
        }
        _uiState.value.selectedNote?.let { note ->
            updateNote(note = note)
        }
    }

    fun updateOptionsVisibility(showOptions: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                showNoteOptions = showOptions
            )
        }
    }

    fun updateCategoryPopupVisibility(showPopup: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(showCategoryPopup = showPopup)
        }
    }

    fun setupNoteOptions() {
        val selectedNote = _uiState.value.selectedNote

        if (selectedNote != null) {

            val options = selectedNote.getOptions(
                onCategoryClick = {
                    updateCategoryPopupVisibility(true)
                },
                onScheduleClick = {}, // TODO setup scheduling
                onProtectClick = {}, // TODO setup note protection
                onDeleteClick = { deleteNote(selectedNote) }
            )

            _uiState.update { currentState ->
                currentState.copy(
                    noteOptions = options
                )
            }
        }
    }

    private fun deleteNote(note: Note) {
        viewModelScope.launch {
            try {
                notesRepository.deleteNote(note.toEntity())
            } catch (ex: Exception) {
                println("Could not DELETE this note: ${ex.message}")
            } finally {
                fetchNotes()
            }
        }
    }

    private fun updateNote(note: Note) {
        viewModelScope.launch {
            try {
                notesRepository.updateNote(note)
            } catch (ex: Exception) {
                println("Could not UPDATE this note: ${ex.message}")
            } finally {
                fetchNotes()
            }
        }
    }
}