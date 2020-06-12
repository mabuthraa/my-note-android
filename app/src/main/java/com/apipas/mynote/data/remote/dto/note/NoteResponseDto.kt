package com.apipas.mynote.data.remote.dto.note

import com.squareup.moshi.Json

data class NoteResponseDto(
    @Json(name = "id")
    val id: String = "",
    @Json(name = "title")
    val title: String = ""
)