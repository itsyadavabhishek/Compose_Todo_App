package com.example.composetodoapp.presentation.utils

sealed class Screen(val route: String) {
    object NotesScreen : Screen("note Screen")
    object AddEditNoteScreen : Screen("add edit note screen")
}
