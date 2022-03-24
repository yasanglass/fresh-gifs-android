package dev.yasan.fresh.gifs.data.network

import dev.yasan.fresh.gifs.model.giphy.GiphyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GiphyAPI {

    @Headers("Accept: application/json")
    @GET("v1/gifs/trending")
    suspend fun fetchTrendingGifs(
        @Query("api_key") apiKey: String,
    ): Response<GiphyResponse>

    @Headers("Accept: application/json")
    @GET("v1/gifs/trending")
    suspend fun fetchQueriedGifs(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
    ): Response<GiphyResponse>

}