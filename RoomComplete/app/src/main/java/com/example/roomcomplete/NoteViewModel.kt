package com.example.roomcomplete

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: NoteRepository
    val notes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        repo = NoteRepository(dao)
        notes = repo.notes.asLiveData()
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repo.addNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repo.deleteNote(note)
    }
}
