package com.toptracer.virgiliomagalhaes.repository.giphy.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphySuccessResponse(
    val data: List<GiphyData>,
    val pagination: Pagination,
    val meta: Meta
)