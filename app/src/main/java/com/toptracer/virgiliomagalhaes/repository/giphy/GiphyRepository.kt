package com.toptracer.virgiliomagalhaes.repository.giphy

import com.toptracer.virgiliomagalhaes.repository.api.GiphyApiService
import com.toptracer.virgiliomagalhaes.repository.giphy.data.GiphyData
import com.toptracer.virgiliomagalhaes.repository.network.NetworkResponse
import java.io.IOException

class GiphyRepository(private val giphyApiService: GiphyApiService) {

    suspend fun getTrendingGiphy() : Result<GiphyData>{
        return try {
            val giphyResponse = giphyApiService.getGifTrending()

             when (giphyResponse) {
                is NetworkResponse.Success -> Result.success(giphyResponse.body.data[0])
                is NetworkResponse.ApiError -> Result.failure(IOException(giphyResponse.body.message))
                is NetworkResponse.NetworkError -> Result.failure(IOException("NETWORK UNAVAILABLE"))
                is NetworkResponse.UnknownError -> Result.failure(IOException("UnknownError"))
            }
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}