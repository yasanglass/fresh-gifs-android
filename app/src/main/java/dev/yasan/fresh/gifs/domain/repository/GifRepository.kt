package dev.yasan.fresh.gifs.domain.repository

import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.fresh.gifs.model.giphy.Gif
import dev.yasan.kit.core.Resource

interface GifRepository {

    /**
     * @return A list of trending [Gif]s.
     */
    suspend fun getTrendingGifs(): Resource<List<FlatGif>>

    /**
     * @return A list of [Gif]s that match the given query.
     */
    suspend fun getQueriedGifs(query: String): Resource<List<FlatGif>>

}