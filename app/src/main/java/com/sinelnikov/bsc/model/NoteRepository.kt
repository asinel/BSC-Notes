package com.sinelnikov.bsc.model

import androidx.lifecycle.MutableLiveData
import com.sinelnikov.bsc.util.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteRepository(private val noteService: NoteService, private val contextPool: CoroutineContextProvider) {

    fun getNotes(): MutableLiveData<Resource<List<PublishedNote>>> {
        val liveData = MutableLiveData<Resource<List<PublishedNote>>>()
        refreshNotes(liveData)
        return liveData
    }

    fun refreshNotes(liveData: MutableLiveData<Resource<List<PublishedNote>>>) {
        liveData.postValue(Resource.loading(liveData.value?.data))
        CoroutineScope(contextPool.IO).launch {
            try {
                liveData.postValue(Resource.success(noteService.getNotes()))
            } catch (e: Exception) {
                liveData.postValue(Resource.error(e.localizedMessage, liveData.value?.data))
            }
        }
    }

    fun createNote(text: String, liveData: MutableLiveData<Resource<PublishedNote>>) {
        liveData.postValue(Resource.loading(liveData.value?.data))
        CoroutineScope(contextPool.IO).launch {
            try {
                liveData.postValue(Resource.success(noteService.createNote(Note(text))))
            } catch (e: Exception) {
                liveData.postValue(Resource.error(e.localizedMessage, liveData.value?.data))
            }
        }
    }

    fun updateNote(newText: String, liveData: MutableLiveData<Resource<PublishedNote>>) {
        val note = liveData.value?.data!!
        liveData.postValue(Resource.loading(liveData.value?.data))
        CoroutineScope(contextPool.IO).launch {
            try {
                liveData.postValue(Resource.success(noteService.updateNote(note.id, Note(newText))))
            } catch (e: Exception) {
                liveData.postValue(Resource.error(e.localizedMessage, note))
            }
        }
    }

    fun deleteNote(id: Int, liveData: MutableLiveData<Resource<Nothing>>) {
        liveData.postValue(Resource.loading(null))
        CoroutineScope(contextPool.IO).launch {
            try {
                noteService.removeNote(id)
                liveData.postValue(Resource.success(null))
            } catch (e: Exception) {
                liveData.postValue(Resource.error(e.localizedMessage, null))
            }
        }
    }
}