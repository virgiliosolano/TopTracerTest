package com.toptracer.virgiliomagalhaes.repository.api

import com.squareup.moshi.Moshi
import com.toptracer.virgiliomagalhaes.BuildConfig
import com.toptracer.virgiliomagalhaes.repository.giphy.data.Error
import com.toptracer.virgiliomagalhaes.repository.giphy.data.GiphySuccessResponse
import com.toptracer.virgiliomagalhaes.repository.network.NetworkResponse
import com.toptracer.virgiliomagalhaes.repository.network.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApiService {
    @GET("gifs/trending")
    suspend fun getGifTrending(@Query("api_key") apikey: String = BuildConfig.API_KEY,
                               @Query("limit") limit: Int = 1,
                               @Query("rating") rating: String = "g"): NetworkResponse<GiphySuccessResponse, Error>

    companion object {
        private val moshi = Moshi.Builder().build()
        private val okHttpClient = OkHttpClient.Builder().build()

        fun createGiphyApiService(): GiphyApiService {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
                .also { retrofit ->
                    return retrofit.create()
                }
        }
    }
}