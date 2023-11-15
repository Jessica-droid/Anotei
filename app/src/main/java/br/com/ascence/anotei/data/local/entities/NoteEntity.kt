package br.com.ascence.anotei.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.NoteStatus

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: NoteEntityType = NoteEntityType.TEXT,
    val title: String = "",
    val status: List<NoteStatus> = emptyList(),
    val category: Category = Category.DEFAULT,
    @ColumnInfo(name = "creation_date") val creationDateInMillis: Long,
    val description: String? = null,
    val itemsList: List<String>? = null,
) {
    enum class NoteEntityType {
        TEXT,
        TEXT_LIST
    }

}