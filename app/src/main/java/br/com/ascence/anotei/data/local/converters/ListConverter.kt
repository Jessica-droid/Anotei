package br.com.ascence.anotei.data.local.converters

import androidx.room.TypeConverter
import br.com.ascence.anotei.data.local.extension.fromJson
import com.google.gson.Gson

class ListConverter {

    @TypeConverter
    fun stringListToJson(value: MutableList<String>): String =
       Gson().toJson(value)

    @TypeConverter
    fun stringListFromJson(json: String): MutableList<String> =
        try {
            Gson().fromJson<MutableList<String>>(json)
        }catch (e: Exception){
            mutableListOf()
        }
}