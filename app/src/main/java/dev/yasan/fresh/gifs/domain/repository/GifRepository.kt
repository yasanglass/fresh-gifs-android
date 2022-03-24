package dev.yasan.fresh.gifs.domain.repository

import dev.yasan.fresh.gifs.model.giphy.Gif

interface GifRepository {

    /**
     * @return A list of trending [Gif]s.
     */
    suspend fun getTrendingGifs(): List<Gif>

    /**
     * @return A list of [Gif]s that match the given query.
     */
    suspend fun getQueriedGifs(query: String): List<Gif>

}