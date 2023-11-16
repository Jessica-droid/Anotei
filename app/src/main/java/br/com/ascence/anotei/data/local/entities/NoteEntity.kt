package br.com.ascence.anotei.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: NoteEntityType = NoteEntityType.TEXT,
    val title: String = "",
    val status: List<NoteEntityStatus> = emptyList(),
    val category: NoteEntityCategory = NoteEntityCategory.DEFAULT,
    @ColumnInfo(name = "creation_date") val creationDateInMillis: Long,
    val description: String? = null,
    val itemsList: List<String>? = null,
) {
    enum class NoteEntityType {
        TEXT,
        TEXT_LIST
    }

    enum class NoteEntityStatus {
        PROTECTED,
        SCHEDULED
    }

    enum class NoteEntityCategory {
        DEFAULT,
        PURPLE,
        PINK,
        RED,
        GREEN
    }
}