package br.com.ascence.anotei.ui.screens.note

import br.com.ascence.anotei.model.Category

data class NoteScreenState(
    val showContentAlert: Boolean = false,
    val showEmptyNoteAlert: Boolean = false,
    val showNoteDiscardAlert : Boolean = false,
    val showCategoryPopup: Boolean = false,
    val title: String = "",
    val creationDate: String = "",
    val description: String = "",
    val noteCategory: Category = Category.DEFAULT
)