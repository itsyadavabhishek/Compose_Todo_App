package com.example.composetodoapp.domain.use_case

import com.example.composetodoapp.domain.model.Note
import com.example.composetodoapp.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(
        id:Int
    ): Note?{
        return repository.getNoteById(id)

    }
}