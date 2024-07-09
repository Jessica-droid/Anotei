package br.com.ascence.anotei.ui.screens.dashboard

import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption

data class DashBoardState(
    val notesList: List<Note> = emptyList(),
    val noteOptions: List<NoteOption> = emptyList(),
    val showNoteOptions: Boolean = false,
    val selectedNoteList: List<Note> = emptyList(),
    val isSelectionModeActivated: Boolean = false,
    val showCategoryPopup: Boolean = false,
    val shouldResetListScroll: Boolean = false,
)