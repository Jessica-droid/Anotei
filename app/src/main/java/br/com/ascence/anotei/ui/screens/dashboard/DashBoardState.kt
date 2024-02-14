package br.com.ascence.anotei.ui.screens.dashboard

import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption

data class DashBoardState(
    val notesList: List<Note> = emptyList(),
    val noteOptions: List<NoteOption> = emptyList(),
    val showNoteOptions: Boolean = false,
    val selectedNote: Note? = null,
    val showCategoryPopup: Boolean = false,
)