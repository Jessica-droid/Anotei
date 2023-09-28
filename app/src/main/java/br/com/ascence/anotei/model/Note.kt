package br.com.ascence.anotei.model

data class Note(
    val title: String,
    val description: String,
    val creationDate: String,
    val status: List<NoteStatus>,
)