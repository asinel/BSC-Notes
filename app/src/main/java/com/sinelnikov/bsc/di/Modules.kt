package com.sinelnikov.bsc.di

import com.sinelnikov.bsc.model.NoteRepository
import com.sinelnikov.bsc.model.NoteService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single { provideDefaultOkHttpClient() }
    single { provideRetrofit(get(), getProperty("base_url")) }
    single { provideNoteService(get()) }
}

val appModule = module {
    single { provideNoteRepository(get()) }
}

fun provideDefaultOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()
}

fun provideNoteService(retrofit: Retrofit): NoteService = retrofit.create(NoteService::class.java)

fun provideNoteRepository(noteService: NoteService): NoteRepository = NoteRepository(noteService)