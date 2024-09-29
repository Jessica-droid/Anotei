package br.com.ascence.anotei.navigation.extensions

import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType
import br.com.ascence.anotei.ui.common.components.noteoptions.presentation.NoteOptionsMode

internal fun NoteType.toNoteOptionsMode() =
    when (this) {
        NoteType.NEW_NOTE -> NoteOptionsMode.CREATE_MODE
        NoteType.UPDATE_NOTE, NoteType.DISPLAY_NOTE -> NoteOptionsMode.EDIT_MODE
    }