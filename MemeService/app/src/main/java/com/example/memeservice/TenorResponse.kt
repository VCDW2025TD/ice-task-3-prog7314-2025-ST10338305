package com.example.memeservice


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TenorResponse(
    val results: List<TenorGif>
)

@JsonClass(generateAdapter = true)
data class TenorGif(
    val id: String,
    val title: String,
    @Json(name = "media_formats") val mediaFormats: MediaFormats
)

@JsonClass(generateAdapter = true)
data class MediaFormats(
    val gif: GifData
)

@JsonClass(generateAdapter = true)
data class GifData(
    val url: String
)
