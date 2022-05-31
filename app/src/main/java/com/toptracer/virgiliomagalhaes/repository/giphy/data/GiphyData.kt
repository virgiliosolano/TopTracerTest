package com.toptracer.virgiliomagalhaes.repository.giphy.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyData(
    val id: String,
    val url: String,
    val username: String,
    val title: String,
    val images: GiphyImages
)