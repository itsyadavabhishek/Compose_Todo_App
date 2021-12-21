package com.example.composetodoapp.presentation.add_edit_note

import android.os.Message
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.toColor
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetodoapp.domain.model.Note
import com.example.composetodoapp.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _notesTitle = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter title..."
        )
    )
    val noteTitle: State<NoteTextFieldState> = _notesTitle

    private val _notesContent = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter your content."
        )
    )
    val noteContent: State<NoteTextFieldState> = _notesContent

    private var currentNoteId: Int? = null

    private val _notesColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _notesColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("note Id")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _notesTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )

                        _notesContent.value = noteContent.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _notesColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddeditNoteevent) {
        when (event) {
            is AddeditNoteevent.EnteredTitle -> {
                _notesTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddeditNoteevent.ChangeTitleFocus -> {
                _notesTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AddeditNoteevent.EnteredContent -> {
                _notesContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AddeditNoteevent.ChangeContentFocus -> {
                _notesContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AddeditNoteevent.ChangeColor -> {
                _notesColor.value = event.color
            }
            is AddeditNoteevent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.saveNote)
                    } catch (e: Note.InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't Save Note"
                            )
                        )

                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object saveNote : UiEvent()
    }
}