package com.sinelnikov.bsc.ui.note

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sinelnikov.bsc.model.NoteRepository
import com.sinelnikov.bsc.model.PublishedNote
import com.sinelnikov.bsc.model.Resource

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val text = MutableLiveData<String>()
    val liveData = MutableLiveData<Resource<PublishedNote>>()

    fun publishNote() {
        if (liveData.value?.data == null) {
            noteRepository.createNote(text.value?:"", liveData)
        }
    }
}