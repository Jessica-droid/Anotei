package br.com.ascence.anotei.ui.screens.notelist

import br.com.ascence.anotei.model.Note

data class NotesListState(
    val notes: List<Note> = emptyList(),
)