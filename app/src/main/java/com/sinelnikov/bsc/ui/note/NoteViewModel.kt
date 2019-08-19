package com.sinelnikov.bsc.ui.note

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sinelnikov.bsc.model.NoteRepository
import com.sinelnikov.bsc.model.PublishedNote
import com.sinelnikov.bsc.model.Resource

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val text = MutableLiveData<String>()
    val liveData = MutableLiveData<Resource<PublishedNote>>()
    val deleteLiveData = MutableLiveData<Resource<Nothing>>()

    fun init(args: NoteFragmentArgs) {
        if (args.note != null) {
            text.value = args.note.title
            liveData.value = Resource.init(args.note)
        }
    }

    fun publishNote() {
        if (liveData.value?.data == null) {
            noteRepository.createNote(text.value?:"", liveData)
        } else {
            noteRepository.updateNote(text.value?:"", liveData)
        }
    }

    fun deleteNote() {
        if (liveData.value?.data != null) {
            noteRepository.deleteNote(liveData.value!!.data!!.id, deleteLiveData)
        }
    }
}