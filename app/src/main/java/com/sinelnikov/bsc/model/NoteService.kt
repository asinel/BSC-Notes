package com.sinelnikov.bsc.model

import retrofit2.Response
import retrofit2.http.*

interface NoteService {

    @GET("notes")
    suspend fun getNotes(): List<PublishedNote>

    @GET("notes/{id}")
    suspend fun getNote(@Path("id") id: Int): PublishedNote

    @POST("notes")
    suspend fun createNote(@Body note: Note): PublishedNote

    @PUT("notes/{id}")
    suspend fun updateNote(@Path("id") id: Int, @Body note: Note): PublishedNote

    @DELETE("notes/{id}")
    suspend fun removeNote(@Path("id") id: Int): Response<Any>
}