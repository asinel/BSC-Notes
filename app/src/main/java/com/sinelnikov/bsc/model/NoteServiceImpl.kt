package com.sinelnikov.bsc.model

import retrofit2.Response
import java.lang.Exception

class NoteServiceImpl: NoteService {

    companion object {
        private const val DELAY = 1000L
    }

    private val notes = mutableMapOf<Int, String>()

    override suspend fun getNotes(): List<PublishedNote> {
        Thread.sleep(DELAY)
        return notes.map { PublishedNote(it.key, it.value) }
    }

    override suspend fun getNote(id: Int): PublishedNote {
        Thread.sleep(DELAY)
        return PublishedNote(id, notes[id]!!)
    }

    override suspend fun createNote(note: Note): PublishedNote {
        Thread.sleep(DELAY)
        val key = (notes.map { it.key }.max()?:-1) + 1
        notes[key] = note.title
        return PublishedNote(key, note.title)
    }

    override suspend fun updateNote(id: Int, note: Note): PublishedNote {
        Thread.sleep(DELAY)
        if (!notes.containsKey(id)) {
            throw Exception("There are no such item")
        }
        notes[id] = note.title
        return PublishedNote(id, note.title)
    }

    override suspend fun removeNote(id: Int): Response<Any> {
        Thread.sleep(DELAY)
        notes.remove(id)
        return Response.success(Any())
    }
}