package com.example.composetodoapp.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddeditNoteevent{
    data class EnteredTitle(val value:String):AddeditNoteevent()
    data class ChangeTitleFocus(val focusState: FocusState):AddeditNoteevent()
    data class EnteredContent(val value: String):AddeditNoteevent()
    data class ChangeContentFocus(val focusState: FocusState):AddeditNoteevent()
    data class ChangeColor(val color: Int):AddeditNoteevent()
    object SaveNote: AddeditNoteevent()
}
