package com.example.composetodoapp.domain.model

import android.graphics.Color
import android.os.Message
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composetodoapp.ui.theme.*

/**
 * A class that represent all the field required for single note(single rectangle box).
 */
@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }

    class InvalidNoteException(message: String) : Exception(message)
}
