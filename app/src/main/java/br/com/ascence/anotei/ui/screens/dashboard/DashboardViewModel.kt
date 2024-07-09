package br.com.ascence.anotei.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ascence.anotei.data.local.repositories.NotesRepository
import br.com.ascence.anotei.model.Note
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
        updateListScrollState(shouldReset = true)
        viewModelScope.launch {
            notesRepository.getAllNotesStream().collect { entities ->
                _uiState.update { currentState ->
                    currentState.copy(
                        notesList = entities,
                        showNoteOptions = false,
                        isSelectionModeActivated = false,
                        selectedNoteList = emptyList(),
                        showCategoryPopup = false,
                        shouldResetListScroll = true
                    )
                }
            }
        }
        updateListScrollState(shouldReset = false)
    }

    fun toggleSelectionMode(activate: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isSelectionModeActivated = activate
            )
        }
    }

    fun updateNoteSelection(note: Note?) {
        note?.let {
            if (_uiState.value.isSelectionModeActivated) {
                handleNoteSelectionMode(it)
            } else {
                handleSimpleNoteSelection(it)
            }
        } ?: resetSelectionList()
    }

    private fun handleSimpleNoteSelection(note: Note) {
        _uiState.update { currentState ->
            currentState.copy(selectedNoteList = listOf(note), showNoteOptions = true)
        }
    }

    private fun handleNoteSelectionMode(note: Note) {
        val notesList = _uiState.value.selectedNoteList

        if (notesList.isEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    selectedNoteList = listOf(note),
                    showNoteOptions = true
                )
            }
        } else {

            val updatedNoteList = removeOrAddNoteFromSelection(notesList, note)
            val stillHaveSelectedNotes = updatedNoteList.isNotEmpty()

            _uiState.update { currentState ->
                currentState.copy(
                    selectedNoteList = updatedNoteList,
                    isSelectionModeActivated = stillHaveSelectedNotes,
                    showNoteOptions = stillHaveSelectedNotes
                )
            }
        }
    }

    private fun removeOrAddNoteFromSelection(notesList: List<Note>, note: Note): List<Note> {

        val notes = notesList.toMutableList()
        val haveAnyNoteWithSelectedId = notes.any { listNote -> listNote.id == note.id }

        if (haveAnyNoteWithSelectedId) {

            val listNoteIndex = notes.indexOfFirst { listNote ->
                listNote.id == note.id
            }

            notes.removeAt(listNoteIndex)

        } else {
            notes.add(note)
        }

        return notes.toList()
    }

    // TODO refact this action to multi selection
//    fun updateSelectedNoteCategory(category: Category) {
//
//        _uiState.update { currentState ->
//            currentState.copy(selectedNote = currentState.selectedNote?.apply {
//                this.category = category
//            })
//        }
//        _uiState.value.selectedNote?.let { note ->
//            updateNote(note = note)
//        }
//    }

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

    // TODO refact this action to multi selection
//    fun setupNoteOptions() {
//        val selectedNote = _uiState.value.selectedNoteList
//
//        if (selectedNote.isNotEmpty()) {
//
//            val options = selectedNote.getOptions(
//                onCategoryClick = {
//                    updateCategoryPopupVisibility(true)
//                },
//                onScheduleClick = {}, // TODO setup scheduling
//                onProtectClick = {}, // TODO setup note protection
//                onDeleteClick = { deleteNote(selectedNote) }
//            )
//
//            _uiState.update { currentState ->
//                currentState.copy(
//                    noteOptions = options
//                )
//            }
//        }
//    }

    private fun resetSelectionList() {
        _uiState.update { currentState ->
            currentState.copy(selectedNoteList = emptyList())
        }
    }

    private fun updateListScrollState(shouldReset: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                shouldResetListScroll = shouldReset
            )
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