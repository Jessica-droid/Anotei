package br.com.ascence.anotei.data.usecase

import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType

class NoteDisplayManager {

    private var showEditMode: Boolean = false

    fun getEditModeVisibility(): Boolean = showEditMode

    fun setupInitialVisibility(noteType: NoteType) {
        when (noteType) {
            NoteType.NEW_NOTE -> enableEditMode(true)
            NoteType.DISPLAY_NOTE -> enableEditMode(false)
        }
    }

    fun shouldReturnToDisplayMode(noteType: NoteType): Boolean =
        when (noteType) {
            NoteType.NEW_NOTE -> false
             NoteType.DISPLAY_NOTE -> showEditMode
        }

    fun enableEditMode(enable: Boolean): Boolean {
        showEditMode = enable
        return showEditMode
    }
}