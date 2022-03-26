package dev.yasan.fresh.gifs.data.source.network

import dev.yasan.fresh.gifs.model.giphy.GiphyResponse
import dev.yasan.fresh.gifs.util.NetworkConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GiphyAPI {

    @Headers("Accept: application/json")
    @GET("v1/gifs/trending")
    suspend fun fetchTrendingGifs(
        @Query("api_key") apiKey: String = NetworkConstants.GIPHY_API_KEY,
    ): Response<GiphyResponse>

    @Headers("Accept: application/json")
    @GET("v1/gifs/search")
    suspend fun fetchQueriedGifs(
        @Query("q") query: String,
        @Query("api_key") apiKey: String = NetworkConstants.GIPHY_API_KEY,
    ): Response<GiphyResponse>

}