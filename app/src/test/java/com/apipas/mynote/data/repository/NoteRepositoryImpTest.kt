package com.apipas.mynote.data.repository

import com.apipas.mynote.data.remote.client.api.ApiResult
import com.apipas.mynote.data.remote.service.NoteServiceApi
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import retrofit2.HttpException
import retrofit2.Response


class NoteRepositoryImpTest {
    private lateinit var noteServiceApi: NoteServiceApi
    private lateinit var noteRepository: NoteRepository
    private val idToOKRes = "1"
    private val idToErrorRes = "2"
    private val successResponse = ApiResult.Success(Unit)

    @Before
    fun setUp() {

        noteServiceApi = mock<NoteServiceApi>()

        //OK
        val mockResponse1 = mock<Response<Unit>>()
        `when`(mockResponse1.code()) doReturn 204
        `when`(mockResponse1.isSuccessful) doReturn true
        `when`(noteServiceApi.deleteAsync(idToOKRes)) doReturn mockResponse1.toDeferredAsync()

        //Exception
        val mockException: HttpException = mock()
        whenever(mockException.code()).thenReturn(404)
        `when`(noteServiceApi.deleteAsync(idToErrorRes)).thenThrow(mockException)


        noteRepository = NoteRepositoryImp(noteServiceApi)
    }


    //`test deleteNote if passed when 204 OK response
    @Test
    fun testOkOnDelete() =
        runBlocking { assertEquals(successResponse, noteRepository.deleteNote(idToOKRes)) }

    //`test deleteNote if return Error passed when HttpException has been thrown
    @Test
    fun testErrorOnDelete() =
        runBlocking {
            assertTrue(noteRepository.deleteNote(idToErrorRes) is ApiResult.Error)
        }
}

fun <T> T.toDeferredAsync() = GlobalScope.async { this@toDeferredAsync }