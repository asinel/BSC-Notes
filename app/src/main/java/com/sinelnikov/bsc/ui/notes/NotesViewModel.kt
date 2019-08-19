package com.sinelnikov.bsc.ui.notes

import androidx.lifecycle.ViewModel
import com.sinelnikov.bsc.model.NoteRepository

class NotesViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val liveData = noteRepository.getNotes()

    fun onRefresh() {
        noteRepository.refreshNotes(liveData)
    }

}