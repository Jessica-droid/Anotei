package br.com.ascence.anotei.ui.screens.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ascence.anotei.data.local.repositories.NotesRepository
import br.com.ascence.anotei.data.usecase.NoteDisplayManager
import br.com.ascence.anotei.data.usecase.alert.NoteAlertManager
import br.com.ascence.anotei.model.AlertType
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.navigation.NOTE_RESULT_CREATED_OR_UPDATED
import br.com.ascence.anotei.navigation.NOTE_RESULT_NOTHING
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class NoteScreenViewModel(
    private val notesRepository: NotesRepository,
    private val alertService: NoteAlertManager = NoteAlertManager(),
    private val displayManager: NoteDisplayManager = NoteDisplayManager(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteScreenState(textNote = createEmptyNote()))
    val screenState: StateFlow<NoteScreenState> = _uiState.asStateFlow()

    fun onTitleUpdate(title: String) {
        if (title.length <= TITLE_MAX_LENGTH) {

            alertService.setupFotContentChangeOnly()

            _uiState.update { currentState ->
                currentState.copy(
                    textNote = currentState.textNote.copy(title = title)
                )
            }
        }
    }

    fun onDescriptionUpdate(description: String) {

        alertService.setupAlertsForNoteDescriptionChange(
            isContentEmpty = description.trimStart().isEmpty()
        )

        _uiState.update { currentState ->
            currentState.copy(
                textNote = currentState.textNote.copy(description = description)
            )
        }
    }

    fun fetchNoteContent(noteType: NoteType, noteId: String? = null) {

        displayManager.setupInitialVisibility(noteType)

        when (noteType) {
            NoteType.NEW_NOTE -> {

                alertService.setupAlertsForNoteCreation()

                _uiState.update { currentState ->
                    currentState.copy(
                        showEditMode = displayManager.getEditModeVisibility(),
                    )
                }
            }

            NoteType.DISPLAY_NOTE -> {
                noteId?.let {
                    viewModelScope.launch {
                        notesRepository.getNoteStream(it.toInt()).collect { note ->

                            alertService.setupAlertsForNoteUpdateOrEdit()

                            _uiState.update { currentState ->
                                currentState.copy(
                                    showEditMode = displayManager.getEditModeVisibility(),
                                    contentAlert = null,
                                    textNote = note as Note.TextNote,
                                )
                            }
                        }
                    }
                }
            }
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
                contentAlert = null,
                showCategoryPopup = false,
            )
        }
    }

    fun handleOnBackPressed(
        noteType: NoteType,
        onCloseScreen: (String) -> Unit,
    ) {

        alertService.setupAlertForBackNavigation()

        _uiState.update { currentState ->
            currentState.copy(
                contentAlert = alertService.checkForAlerts(
                    noteType = noteType,
                    isEditModeEnabled = displayManager.getEditModeVisibility()
                ),
            )
        }

        if (_uiState.value.contentAlert == null) {
            dismissOrDisplayMode(noteType, onCloseScreen)
        }
    }

    fun handleAlertOption(
        noteType: NoteType,
        noteId: String?,
        alertType: AlertType,
        onCloseScreen: (String) -> Unit,
    ) {
        when (alertType) {
            AlertType.CONFIRM_NOTE_DISCARD -> dismissContentAlertDialog(
                noteType,
                noteId,
                onCloseScreen
            )

            AlertType.EMPTY_NOTE_CONTENT -> hideAlertDialog()
            AlertType.DELETE_NOTE_CONFIRMATION -> hideAlertDialog()
        }
    }

    fun handleNote(noteType: NoteType, note: Note?, onSaveNote: (String) -> Unit) {

        alertService.setupAlertForSavingNote()

        _uiState.update { currentState ->
            currentState.copy(
                contentAlert = alertService.checkForAlerts(
                    noteType = noteType,
                    isEditModeEnabled = displayManager.getEditModeVisibility()
                ),
            )
        }

        if (_uiState.value.contentAlert == null) {
            saveOrUpdateNote(noteType, note, onSaveNote)
        }

    }

    fun enableEditMode() {
        _uiState.update { currentState ->
            currentState.copy(
                showEditMode = displayManager.enableEditMode(enable = true)
            )
        }
    }

    fun updateNoteCategory(category: Category) {
        _uiState.update { currentState ->
            currentState.copy(
                textNote = currentState.textNote.copy(category = category),
            )
        }
    }

    private fun dismissContentAlertDialog(
        noteType: NoteType,
        noteId: String? = null,
        onCloseScreen: (String) -> Unit,
    ) {
        when (noteType) {
            NoteType.NEW_NOTE -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        contentAlert = null
                    )
                }
                onCloseScreen(NOTE_RESULT_NOTHING)
            }

            NoteType.DISPLAY_NOTE,
            -> fetchNoteContent(noteType, noteId)
        }
    }

    fun dismissOrDisplayMode(noteType: NoteType, onCloseScreen: (String) -> Unit) {
        val shouldBackToDisplayMode = displayManager.shouldReturnToDisplayMode(noteType)

        if (shouldBackToDisplayMode) {
            _uiState.update { currentState ->
                currentState.copy(
                    showEditMode = displayManager.enableEditMode(enable = false),
                    contentAlert = null
                )
            }
        } else {
            onCloseScreen(NOTE_RESULT_NOTHING)
        }
    }

    private fun showDeleteNoteAlert() {
        _uiState.update { currentState ->
            currentState.copy(
                contentAlert = AlertType.DELETE_NOTE_CONFIRMATION
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
            NoteType.DISPLAY_NOTE -> note?.let {
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
            title = setupNoteTitle(_uiState.value.textNote.title),
            status = emptyList(),
            category = _uiState.value.textNote.category,
            creationDate = note.creationDate,
            description = _uiState.value.textNote.description
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

    private fun newTextNoteSetup(): Note.TextNote =
        Note.TextNote(
            id = NEW_NOTE_ID,
            title = _uiState.value.textNote.title.let { setupNoteTitle(it) },
            status = emptyList(), // TODO setup note status
            category = _uiState.value.textNote.category,
            creationDate = Date(),
            description = _uiState.value.textNote.description
        )

    private fun createEmptyNote(): Note.TextNote =
        Note.TextNote(
            id = NEW_NOTE_ID,
            title = "",
            status = emptyList(),
            description = "",
            creationDate = Date(),
            category = Category.DEFAULT
        )

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