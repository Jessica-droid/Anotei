package br.com.ascence.anotei.ui.screens.note

import br.com.ascence.anotei.model.AlertType
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption

data class NoteScreenState(
    val showCategoryPopup: Boolean = false,
    val showEditMode: Boolean = false,
    val noteCategory: Category = Category.DEFAULT,
    val noteOptions: List<NoteOption> = emptyList(),
    val textNote: Note.TextNote,
    val contentAlert: AlertType? = null,
)