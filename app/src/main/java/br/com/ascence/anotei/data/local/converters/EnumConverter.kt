package br.com.ascence.anotei.data.local.converters

import androidx.room.TypeConverter
import br.com.ascence.anotei.data.local.entities.NoteEntity.NoteEntityStatus

class EnumConverter {

    @TypeConverter
    fun stringToEnumStatusList(value: String): MutableList<NoteEntityStatus> =
        if (value.isNotEmpty()) {

            val stringStatusList = value.split(",")

            stringStatusList.map { statusString ->
                NoteEntityStatus.valueOf(statusString)
            }.toMutableList()
        } else {
            mutableListOf()
        }


    @TypeConverter
    fun enumStatusListToString(value: MutableList<NoteEntityStatus>): String =
        value.joinToString(separator = ",") { enumStatus ->
            enumStatus.name
        }
}