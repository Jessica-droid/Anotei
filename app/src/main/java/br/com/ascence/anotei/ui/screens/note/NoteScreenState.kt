package br.com.ascence.anotei.ui.screens.note

import br.com.ascence.anotei.model.Category

data class NoteScreenState(
    val showContentAlert: Boolean = false,
    val showEmptyNoteAlert: Boolean = false,
    val title: String = "Sem título",
    val creationDate: String = "",
    val description: String = "",
    val noteCategory: Category = Category.DEFAULT
)