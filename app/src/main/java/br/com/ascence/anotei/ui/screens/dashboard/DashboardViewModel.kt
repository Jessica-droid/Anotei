package br.com.ascence.anotei.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ascence.anotei.data.local.repositories.NotesRepository
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
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
                        isSelectionModeActivated = false,
                        selectedNoteList = emptyList(),
                        showCategoryPopup = false,
                        shouldResetListScroll = false
                    )
                }
            }
        }
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

    fun updateSelectedNoteCategory(category: Category) {

        if (_uiState.value.isSelectionModeActivated) {
            updateCategoryRange(category)
        } else {
            updateNoteCategory(category = category)
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

    fun resetScreenState() {
        _uiState.update { currentState ->
            currentState.copy(
                selectedNoteList = emptyList(),
                showNoteOptions = false,
                showCategoryPopup = false,
                isSelectionModeActivated = false
            )
        }
    }

    fun handleNoteOptionClick(option: NoteOption) {
        when (option) {
            is NoteOption.Category -> updateCategoryPopupVisibility(true)
            is NoteOption.Delete -> handleNoteDeletion()
            is NoteOption.Protect -> TODO()
            is NoteOption.Schedule -> TODO()
        }
    }

    fun resetListScrollState() {
        _uiState.update { currentState ->
            currentState.copy(
                shouldResetListScroll = true
            )
        }
    }

    private fun handleNoteDeletion(){

        val isSelectionModeActivated = _uiState.value.isSelectionModeActivated

        if (isSelectionModeActivated) {
            deleteNoteRange()
        } else {
            val selectedNote = _uiState.value.selectedNoteList.firstOrNull()
            selectedNote?.let { deleteNote(it) }
        }
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

    private fun resetSelectionList() {
        _uiState.update { currentState ->
            currentState.copy(selectedNoteList = emptyList())
        }
    }

    private fun updateCategoryRange(category: Category) {
        val selectedNotesId = _uiState.value.selectedNoteList.map { note -> note.id }

        viewModelScope.launch {
            try {
                notesRepository.updateCategoryRange(
                    category = category,
                    idList = selectedNotesId
                )
            } catch (ex: Exception) {
                println("Could not UPDATE this range of notes: ${ex.message}")
            } finally {
                fetchNotes()
            }
        }
    }

    private fun deleteNote(note: Note) {
        viewModelScope.launch {
            try {
                notesRepository.deleteNote(note)
            } catch (ex: Exception) {
                println("Could not DELETE this note: ${ex.message}")
            } finally {
                fetchNotes()
            }
        }
    }

    private fun deleteNoteRange() {
        val selectedNotesId = _uiState.value.selectedNoteList.map { note -> note.id }

        viewModelScope.launch {
            try {
                notesRepository.deleteNoteRange(
                    idList = selectedNotesId
                )
            } catch (ex: Exception) {
                println("Could not DELETE this range of notes: ${ex.message}")
            } finally {
                fetchNotes()
            }
        }
    }

    private fun updateNoteCategory(category: Category) {

        val selectedNote = _uiState.value.selectedNoteList.firstOrNull()

        selectedNote?.let {
            viewModelScope.launch {
                try {
                    notesRepository.updateNote(it.apply { it.category = category })
                } catch (ex: Exception) {
                    println("Could not UPDATE this note: ${ex.message}")
                } finally {
                    fetchNotes()
                }
            }
        } ?: println("NO CATEGORY SELECTED TO UPDATE")
    }
}