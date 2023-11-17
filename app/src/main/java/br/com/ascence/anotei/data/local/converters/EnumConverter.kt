package br.com.ascence.anotei.data.local.converters

import androidx.room.TypeConverter
import br.com.ascence.anotei.data.local.entities.NoteEntity.NoteEntityStatus

class EnumConverter {

    @TypeConverter
    fun stringToEnumStatusList(value: String): MutableList<NoteEntityStatus> {
        val stringStatusList = value.split(",")

        return stringStatusList.map { statusString ->
            NoteEntityStatus.valueOf(statusString)
        }.toMutableList()
    }

    @TypeConverter
    fun enumStatusListToString(value: MutableList<NoteEntityStatus>): String =
        value.joinToString(separator = ",") { enumStatus ->
            enumStatus.name
        }
}