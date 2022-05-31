package com.toptracer.virgiliomagalhaes.repository.giphy.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyImages(
    val original: OriginalImage
)

@JsonClass(generateAdapter = true)
data class OriginalImage(
    val url: String
)