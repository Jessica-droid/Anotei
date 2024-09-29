package br.com.ascence.anotei.ui.screens.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ascence.anotei.data.local.repositories.NotesRepository
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.navigation.NOTE_RESULT_CREATED_OR_UPDATED
import br.com.ascence.anotei.navigation.NOTE_RESULT_NOTHING
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType
import br.com.ascence.anotei.utils.date.DateHelper
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
                    title = title,
                    descriptionOrTitleHasChanged = true
                )
            }
        }
    }

    fun onDescriptionUpdate(description: String) {
        _uiState.update { currentState ->
            currentState.copy(
                description = description,
                descriptionOrTitleHasChanged = true
            )
        }
    }

    fun fetchNoteContent(noteType: NoteType, noteId: String? = null) {
        when (noteType) {
            NoteType.NEW_NOTE -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        creationDate = DateHelper().formatDateToString(Date()),
                        showEditMode = true,
                        descriptionOrTitleHasChanged = false
                    )
                }
            }

            NoteType.UPDATE_NOTE, NoteType.DISPLAY_NOTE -> {
                noteId?.let {
                    viewModelScope.launch {
                        notesRepository.getNoteStream(it.toInt()).collect { note ->
                            val textNote = note as Note.TextNote
                            _uiState.update { currentState ->
                                currentState.copy(
                                    title = textNote.title,
                                    creationDate = DateHelper().formatDateToString(textNote.creationDate),
                                    description = textNote.description,
                                    noteCategory = textNote.category,
                                    showEditMode = noteType == NoteType.UPDATE_NOTE,
                                    descriptionOrTitleHasChanged = false,
                                    showContentAlert = false,
                                )
                            }
                        }
                    }
                }

//                note?.let {
//                    _uiState.update { currentState ->
//                        currentState.copy(
//                            title = note.title,
//                            creationDate = DateHelper().formatDateToString(note.creationDate),
//                            description = note.description,
//                            noteCategory = note.category,
//                            showEditMode = true
//                        )
//                    }
//                }
            }

//            NoteType.DISPLAY_NOTE -> {
//                note?.let {
//                    _uiState.update { currentState ->
//                        currentState.copy(
//                            title = note.title,
//                            creationDate = DateHelper().formatDateToString(note.creationDate),
//                            description = note.description,
//                            noteCategory = note.category,
//                            showEditMode = false
//                        )
//                    }
//                }
//            }
        }
    }

    fun handleNoteOptionClick(option: NoteOption) {
        when (option) {
            is NoteOption.Category -> showCategoryPopup()
            is NoteOption.Delete -> showDeleteNoteAlert()
            is NoteOption.Protect -> {} // TODO
            is NoteOption.Schedule -> {} // TODO
        }
    }

    fun hideAlertDialog() {
        _uiState.update { currentState ->
            currentState.copy(
                showContentAlert = false,
                showEmptyNoteAlert = false,
                showNoteDiscardAlert = false,
                showCategoryPopup = false,
            )
        }
    }

    fun handleOnBackPressed(noteType: NoteType, onCloseScreen: (String) -> Unit) {
        val shouldShowContentAlert = shouldShowContentAlert(noteType)

        _uiState.update { currentState ->
            currentState.copy(
                showContentAlert = shouldShowContentAlert,
            )
        }

        if (_uiState.value.showContentAlert.not()) {
            dismissOrDisplayMode(noteType, onCloseScreen)
        }
    }

    fun handleNote(noteType: NoteType, note: Note?, onSaveNote: (String) -> Unit) {
        handleNoteContent()

        if (_uiState.value.showEmptyNoteAlert.not()) {
            saveOrUpdateNote(noteType, note, onSaveNote)
        }
    }

    fun enableEditMode() {
        _uiState.update { currentState ->
            currentState.copy(
                showEditMode = true
            )
        }
    }

    fun updateNoteCategory(category: Category) {
        _uiState.update { currentState ->
            currentState.copy(
                noteCategory = category
            )
        }
    }

    fun dismissContentAlertDialog(
        noteType: NoteType,
        noteId: String? = null,
        onCloseScreen: (String) -> Unit,
    ) {
        when (noteType) {
            NoteType.NEW_NOTE -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        showContentAlert = false
                    )
                }
            }

            NoteType.UPDATE_NOTE,
            NoteType.DISPLAY_NOTE,
            -> {
                if (_uiState.value.showEditMode) {
                    fetchNoteContent(noteType, noteId)
                }
            }
        }

        dismissOrDisplayMode(noteType, onCloseScreen)
    }

    private fun dismissOrDisplayMode(noteType: NoteType, onCloseScreen: (String) -> Unit) {
        val shouldBackToDisplayMode =
            when (noteType) {
                NoteType.UPDATE_NOTE,
                NoteType.DISPLAY_NOTE,
                -> _uiState.value.showEditMode

                NoteType.NEW_NOTE -> false
            }

        if (shouldBackToDisplayMode) {
            _uiState.update { currentState ->
                currentState.copy(
                    showEditMode = false,
                )
            }
        } else {
            onCloseScreen(NOTE_RESULT_NOTHING)
        }
    }

    private fun shouldShowContentAlert(noteType: NoteType): Boolean {
        return when (noteType) {
            NoteType.NEW_NOTE -> _uiState.value.description.isNotEmpty()
            NoteType.UPDATE_NOTE, NoteType.DISPLAY_NOTE -> _uiState.value.descriptionOrTitleHasChanged
        }
    }

    private fun showDeleteNoteAlert() {
        _uiState.update { currentState ->
            currentState.copy(
                showNoteDiscardAlert = true
            )
        }
    }

    private fun showCategoryPopup() {
        _uiState.update { currentState ->
            currentState.copy(
                showCategoryPopup = true
            )
        }
    }

    private fun saveOrUpdateNote(noteType: NoteType, note: Note?, onSaveNote: (String) -> Unit) {
        when (noteType) {
            NoteType.NEW_NOTE -> createNote(onSaveNote)
            NoteType.UPDATE_NOTE, NoteType.DISPLAY_NOTE -> note?.let {
                updateNote(it, onSaveNote)
            }
        }
    }

    private fun createNote(onSaveNote: (String) -> Unit) {
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

    private fun updateNote(note: Note, onSaveNote: (String) -> Unit) {

        val noteToUpdate = Note.TextNote(
            id = note.id,
            title = setupNoteTitle(_uiState.value.title),
            status = emptyList(),
            category = _uiState.value.noteCategory,
            creationDate = note.creationDate,
            description = _uiState.value.description
        )

        viewModelScope.launch {
            runCatching {
                notesRepository.updateNote(noteToUpdate)
            }.onSuccess {
                onSaveNote(NOTE_RESULT_CREATED_OR_UPDATED)
            }.onFailure {
                println(">>>>>> DEU RUIM ${it.message}")
            }
        }
    }

    private fun newTextNoteSetup(): Note =
        Note.TextNote(
            id = NEW_NOTE_ID,
            title = setupNoteTitle(_uiState.value.title),
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

    private fun setupNoteTitle(currentTitle: String) =
        currentTitle.ifBlank {
            NOTE_DEFAULT_TITLE
        }

    companion object {
        const val NOTE_DEFAULT_TITLE = "Sem título"
        const val TITLE_MAX_LENGTH = 40
        private const val NEW_NOTE_ID = 0
    }
}