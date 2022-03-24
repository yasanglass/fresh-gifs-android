package dev.yasan.fresh.gifs.data.repository

import dev.yasan.fresh.gifs.data.network.GiphyAPI
import dev.yasan.fresh.gifs.domain.repository.GifRepository
import dev.yasan.fresh.gifs.model.giphy.Gif
import javax.inject.Inject

class GifRepositoryImp @Inject constructor(
    private val giphyAPI: GiphyAPI
) : GifRepository {

    override suspend fun getTrendingGifs(): List<Gif> {
        val response = giphyAPI.fetchTrendingGifs()
        return response.body()!!.data
        // TODO Handle non successful responses
    }

    override suspend fun getQueriedGifs(query: String): List<Gif> {
        val response = giphyAPI.fetchQueriedGifs(query = query)
        return response.body()!!.data
        // TODO Handle non successful responses
    }

}