package br.com.ascence.anotei.data.usecase.alert

import br.com.ascence.anotei.model.AlertType
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType

class NoteAlertManager {

    private var startingBackNavigation: Boolean = false
    private var isSavingNote: Boolean = false
    private var noteContentIsEmpty: Boolean = true
    private var noteContentHasChanged: Boolean = false

    fun checkForAlerts(noteType: NoteType, isEditModeEnabled: Boolean): AlertType? =
        when (noteType) {
            NoteType.NEW_NOTE -> checkForNoteCreationAlerts(noteType)
            NoteType.DISPLAY_NOTE -> checkForNoteEditingAlerts(isEditModeEnabled)
        }

    fun setupAlertsForNoteCreation() {
        noteContentHasChanged = false
        noteContentIsEmpty = true
    }

    fun setupAlertsForNoteUpdateOrEdit() {
        noteContentHasChanged = false
        noteContentIsEmpty = false
    }

    fun setupAlertsForNoteDescriptionChange(isContentEmpty: Boolean) {
        noteContentHasChanged = true
        noteContentIsEmpty = isContentEmpty
    }

    fun setupFotContentChangeOnly() {
        noteContentHasChanged = true
    }

    fun setupAlertForBackNavigation() {
        startingBackNavigation = true
    }

    fun setupAlertForSavingNote() {
        isSavingNote = true
    }

    private fun checkForNoteCreationAlerts(noteType: NoteType): AlertType? =
        when {
            startingBackNavigation -> checkForNoteDiscardAlert(noteType = noteType)
            isSavingNote -> checkForEmptyNoteAlert()
            else -> null
        }

    private fun checkForNoteDiscardAlert(noteType: NoteType): AlertType? {
        val shouldShowContentAlert = when (noteType) {
            NoteType.NEW_NOTE -> noteContentIsEmpty.not()
            NoteType.DISPLAY_NOTE -> noteContentHasChanged
        }

        return if (shouldShowContentAlert) AlertType.CONFIRM_NOTE_DISCARD else null
    }

    private fun checkForEmptyNoteAlert(): AlertType? =
        if (noteContentIsEmpty) AlertType.EMPTY_NOTE_CONTENT else null

    private fun checkForNoteEditingAlerts(isEditModeEnabled: Boolean): AlertType? =
        when {
            startingBackNavigation && isEditModeEnabled.not() -> null
            startingBackNavigation && isEditModeEnabled -> checkForNoteDiscardAlert(noteType = NoteType.DISPLAY_NOTE)
            isSavingNote -> checkForEmptyNoteAlert()
            else -> null
        }
}