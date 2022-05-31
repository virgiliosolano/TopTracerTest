package com.toptracer.virgiliomagalhaes.repository.giphy.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    val status: Int,
    val msg: String,
    @Json(name = "response_id") val responseId: String
)