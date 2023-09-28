package br.com.ascence.anotei.model

data class Note(
    val id: String,
    val title: String,
    val description: String,
    val creationDate: String,
    val status: List<NoteStatus>,
)