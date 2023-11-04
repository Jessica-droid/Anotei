package br.com.ascence.anotei.model

sealed class Note {
    abstract val id: String
    abstract val title: String
    abstract val status: List<NoteStatus>
    abstract val category: Category
    abstract val creationDate: String

    fun isProtected(): Boolean = status.contains(NoteStatus.PROTECTED)
    fun isScheduled(): Boolean = status.contains(NoteStatus.SCHEDULED)

    data class TextNote(
        override val id: String,
        override val title: String,
        override val status: List<NoteStatus>,
        override val category: Category,
        override val creationDate: String,
        val description: String,
    ) : Note()

}