package com.toptracer.virgiliomagalhaes.repository.giphy.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Error(
    val status: String,
    val message: String
)