package com.example.composetodoapp.data.repository

import com.example.composetodoapp.data.data_source.NoteDao
import com.example.composetodoapp.domain.model.Note
import com.example.composetodoapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoterepositoryImpl(
    private val dao: NoteDao
) : NoteRepository{
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}