package com.example.composetodoapp.domain.use_case

import com.example.composetodoapp.domain.model.Note
import com.example.composetodoapp.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}
