package com.example.composetodoapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composetodoapp.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object{
        public val DATABASE_NAME = "note_db"
    }
}
