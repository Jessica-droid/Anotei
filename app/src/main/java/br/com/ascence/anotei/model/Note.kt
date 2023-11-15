package br.com.ascence.anotei.model

import java.util.Date

sealed class Note {
    abstract val id: Int
    abstract val title: String
    abstract val status: List<NoteStatus>
    abstract val category: Category
    abstract val creationDate: Date

    fun isProtected(): Boolean = status.contains(NoteStatus.PROTECTED)
    fun isScheduled(): Boolean = status.contains(NoteStatus.SCHEDULED)

    data class SimpleText(
        override val id: Int,
        override val title: String,
        override val status: List<NoteStatus>,
        override val category: Category,
        override val creationDate: Date,
        val description: String,
    ) : Note()

    data class TodoList(
        override val id: Int,
        override val title: String,
        override val status: List<NoteStatus>,
        override val category: Category,
        override val creationDate: Date,
        val items: List<String>,
    ) : Note()
}