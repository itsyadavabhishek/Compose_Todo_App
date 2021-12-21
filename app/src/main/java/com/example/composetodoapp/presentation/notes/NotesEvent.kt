package com.example.composetodoapp.presentation.notes

import com.example.composetodoapp.domain.model.Note
import com.example.composetodoapp.domain.utils.NoteOrder

sealed class NotesEvent{
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
