package com.example.composetodoapp.presentation.notes

import com.example.composetodoapp.domain.model.Note
import com.example.composetodoapp.domain.utils.NoteOrder
import com.example.composetodoapp.domain.utils.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible : Boolean = false

)
