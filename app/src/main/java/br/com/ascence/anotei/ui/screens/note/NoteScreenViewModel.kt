package br.com.ascence.anotei.ui.screens.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ascence.anotei.data.local.repositories.NotesRepository
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.navigation.NOTE_RESULT_CREATED_OR_UPDATED
import br.com.ascence.anotei.navigation.NOTE_RESULT_NOTHING
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class NoteScreenViewModel(
    private val notesRepository: NotesRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteScreenState())
    val screenState: StateFlow<NoteScreenState> = _uiState.asStateFlow()

    fun onTitleUpdate(title: String) {
        if (title.length <= TITLE_MAX_LENGTH) {
            _uiState.update { currentState ->
                currentState.copy(
                    title = title
                )
            }
        }
    }

    fun onDescriptionUpdate(description: String) {
        _uiState.update { currentState ->
            currentState.copy(
                description = description
            )
        }
    }

    fun hideContentAlertDialog() {
        _uiState.update { currentState ->
            currentState.copy(
                showContentAlert = false
            )
        }
    }

    fun handleOnBackPressed(onCloseScreen: (String) -> Unit) {
        _uiState.update { currentState ->
            currentState.copy(
                showContentAlert = currentState.description.isNotEmpty()
            )
        }
        if (_uiState.value.showContentAlert.not()) {
            onCloseScreen(NOTE_RESULT_NOTHING)
        }
    }

    fun saveNote(onSaveNote: (String) -> Unit) {

        handleNoteContent()

        if (_uiState.value.showEmptyNoteAlert.not()) {

            val note = newTextNoteSetup()

            viewModelScope.launch {
                runCatching {
                    notesRepository.createNote(note)
                }.onSuccess {
                    onSaveNote(NOTE_RESULT_CREATED_OR_UPDATED)
                }.onFailure {
                    println(">>>>>> DEU RUIM ${it.message}")
                }
            }
        }
    }

    fun hideEmptyNoteAlert() {
        _uiState.update { currentState ->
            currentState.copy(
                showEmptyNoteAlert = false
            )
        }
    }

    private fun newTextNoteSetup(): Note =
        Note.TextNote(
            id = NEW_NOTE_ID,
            title = _uiState.value.title,
            status = emptyList(), // TODO setup note status
            category = _uiState.value.noteCategory,
            creationDate = Date(),
            description = _uiState.value.description
        )

    private fun handleNoteContent() {
        _uiState.update { currentState ->
            currentState.copy(
                showEmptyNoteAlert = currentState.description.trimStart().isEmpty()
            )
        }
    }

    private fun cleanScreenState() {
        _uiState.update { currentState ->
            currentState.copy(
                title = "Sem título",
                description = "",
                showEmptyNoteAlert = false,
                showContentAlert = false,
                noteCategory = Category.DEFAULT
            )
        }
    }

    private companion object {
        const val TITLE_MAX_LENGTH = 40
        const val NEW_NOTE_ID = 0
    }
}