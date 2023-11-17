package br.com.ascence.anotei.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.ascence.anotei.data.local.converters.EnumConverter
import br.com.ascence.anotei.data.local.converters.ListConverter
import br.com.ascence.anotei.data.local.dao.NoteDao
import br.com.ascence.anotei.data.local.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class, EnumConverter::class)
abstract class AnoteiDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        private const val databaseName = "anotei_database"

        @Volatile
        private var Instance: AnoteiDatabase? = null

        fun getDatabase(context: Context): AnoteiDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AnoteiDatabase::class.java, databaseName)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}