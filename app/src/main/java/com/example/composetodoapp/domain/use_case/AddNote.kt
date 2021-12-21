package com.example.composetodoapp.domain.use_case

import com.example.composetodoapp.domain.model.Note
import com.example.composetodoapp.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(Note.InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw Note.InvalidNoteException("this Title is empty!!")
        }else if (note.content.isBlank()) {
            throw Note.InvalidNoteException("this Content is empty!!")
        }

        repository.insertNote(note)

    }
}