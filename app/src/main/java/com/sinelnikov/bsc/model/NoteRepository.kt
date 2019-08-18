package com.sinelnikov.bsc.model

class NoteRepository(private val noteService: NoteService) {

    suspend fun getNotes() = noteService.getNotes()

    suspend fun createNote(note: Note) = noteService.createNote(note)
}