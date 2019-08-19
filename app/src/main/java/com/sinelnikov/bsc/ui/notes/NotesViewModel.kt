package com.sinelnikov.bsc.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sinelnikov.bsc.R
import com.sinelnikov.bsc.model.NoteRepository
import com.sinelnikov.bsc.model.PublishedNote
import com.sinelnikov.bsc.util.Event
import com.sinelnikov.bsc.util.NavigationTransaction

class NotesViewModel(private val noteRepository: NoteRepository) : ViewModel(), NotesAdapter.OnNoteClickListener {

    val liveData = noteRepository.getNotes()
    val navigationLiveData = MutableLiveData<Event<NavigationTransaction<Any>>>()


    fun onRefresh() {
        noteRepository.refreshNotes(liveData)
    }

    fun onCreateNoteClick() {
        navigationLiveData.postValue(Event(NavigationTransaction(R.id.noteFragment, null)))
    }

    override fun onNoteClicked(note: PublishedNote) {
        navigationLiveData.postValue(Event(NavigationTransaction(R.id.noteFragment, note)))
    }
}