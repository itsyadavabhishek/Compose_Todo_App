package com.example.composetodoapp.di

import android.app.Application
import androidx.room.Room
import com.example.composetodoapp.data.data_source.NoteDatabase
import com.example.composetodoapp.data.repository.NoterepositoryImpl
import com.example.composetodoapp.domain.repository.NoteRepository
import com.example.composetodoapp.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDataBase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME

        ).build()
    }

    @Provides
    @Singleton
    fun ProvideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoterepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun ProvidenoteUsecases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }

}