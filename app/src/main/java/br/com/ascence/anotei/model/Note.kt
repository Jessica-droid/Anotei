package br.com.ascence.anotei.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

sealed class Note {
    abstract val id: Int
    abstract val title: String
    abstract val status: List<NoteStatus>
    abstract var category: Category
    abstract val creationDate: Date

    fun isProtected(): Boolean = status.contains(NoteStatus.PROTECTED)
    fun isScheduled(): Boolean = status.contains(NoteStatus.SCHEDULED)

    @Parcelize
    data class TextNote(
        override val id: Int,
        override val title: String,
        override val status: List<NoteStatus>,
        override var category: Category,
        override val creationDate: Date,
        val description: String,
    ) : Note(), Parcelable

    data class TodoList(
        override val id: Int,
        override val title: String,
        override val status: List<NoteStatus>,
        override var category: Category,
        override val creationDate: Date,
        val items: List<String>,
    ) : Note()
}