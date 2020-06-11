package com.apipas.mynote.data.remote.service

import com.apipas.mynote.data.remote.dto.note.NoteRequestDto
import com.apipas.mynote.data.remote.dto.note.NoteResponseDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface NoteServiceApi {

    @GET("notes")
    fun getNotesAsync(): Deferred<Response<List<NoteResponseDto>>>

    @GET("notes/{noteId}")
    fun getNoteAsync(@Path("noteId") noteId: String): Deferred<Response<NoteResponseDto>>

    @POST("notes")
    fun createNotesAsync(@Body body: NoteRequestDto): Deferred<Response<NoteResponseDto>>

    @PUT("notes/{noteId}")
    fun updateNotAsync(
        @Path("noteId") noteId: String,
        @Body body: NoteRequestDto
    ): Deferred<Response<NoteResponseDto>>

    @DELETE("notes/{noteId}")
    fun deleteAsync(@Path("noteId") noteId: String): Deferred<Response<Unit>>
}