package com.sinelnikov.bsc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sinelnikov.bsc.model.NoteRepository
import com.sinelnikov.bsc.model.PublishedNote
import com.sinelnikov.bsc.ui.note.NoteFragmentArgs
import com.sinelnikov.bsc.ui.note.NoteViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify


class NoteViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var repository: NoteRepository
    lateinit var viewModel: NoteViewModel

    @Before
    fun before() {
        repository = Mockito.mock(NoteRepository::class.java)
        viewModel = NoteViewModel(repository)
    }

    @Test
    fun createNote() {
        val text = "Test note 1"
        viewModel.text.postValue(text)
        viewModel.publishNote()
        verify(repository).createNote(text, viewModel.liveData)
    }

    @Test
    fun updateNote() {
        viewModel.init(NoteFragmentArgs(PublishedNote(2, "Test note 1")))
        val text = "Test note 2"
        viewModel.text.postValue(text)
        viewModel.publishNote()
        verify(repository).updateNote(text, viewModel.liveData)
    }

    @Test
    fun deleteNoteCorrect() {
        viewModel.init(NoteFragmentArgs(PublishedNote(2, "Test note 1")))
        viewModel.deleteNote()
        verify(repository).deleteNote(2, viewModel.deleteLiveData)
    }

    @Test
    fun deleteNoteInCorrect() {
        viewModel.deleteNote()
        verify(repository, never()).deleteNote(2, viewModel.deleteLiveData)
    }
}