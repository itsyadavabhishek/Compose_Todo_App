package com.example.composetodoapp.domain.use_case

import com.example.composetodoapp.domain.model.Note
import com.example.composetodoapp.domain.repository.NoteRepository
import com.example.composetodoapp.domain.utils.NoteOrder
import com.example.composetodoapp.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Usecase for getting the notes and have feature for ascendind and desecding
 */
class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(
            OrderType.Descending)): Flow<List<Note>> {
        return repository.getNotes().map { notes->
            when ( noteOrder.orderType){
                is OrderType.Ascending->{
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }

                is OrderType.Descending->{
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }

            }
        }

    }
}