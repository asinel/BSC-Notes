package com.sinelnikov.bsc.di

import com.sinelnikov.bsc.model.NoteRepository
import com.sinelnikov.bsc.model.NoteService
import com.sinelnikov.bsc.ui.note.NoteViewModel
import com.sinelnikov.bsc.ui.notes.NotesViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideDefaultOkHttpClient() }
    single { provideRetrofit(get(), getProperty("base_url")) }
    single { provideNoteService(get()) }
}

val appModule = module {
    single { provideNoteRepository(get()) }
    viewModel { NotesViewModel(get()) }
    viewModel { NoteViewModel(get()) }
}

private fun provideDefaultOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

private fun provideNoteService(retrofit: Retrofit): NoteService = retrofit.create(NoteService::class.java)

private fun provideNoteRepository(noteService: NoteService): NoteRepository = NoteRepository(noteService)