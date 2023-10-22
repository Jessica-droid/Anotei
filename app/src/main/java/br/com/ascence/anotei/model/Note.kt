package br.com.ascence.anotei.model

data class Note(
    val id: String,
    val title: String,
    val description: String,
    val creationDate: String,
    val category: Category,
    val status: List<NoteStatus>,
){
    val isProtected: Boolean = status.contains(NoteStatus.PROTECTED)
    val isScheduled: Boolean = status.contains(NoteStatus.SCHEDULED)
}