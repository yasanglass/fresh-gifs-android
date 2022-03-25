package dev.yasan.fresh.gifs.domain.repository

import androidx.lifecycle.LiveData
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif

interface FavoriteRepository {

    /**
     * @return A [List] of favorite [FlatGif]s as a [LiveData].
     */
    fun getFavoriteGifs(): LiveData<List<FlatGif>>

    /**
     * Adds the GIF to the favorites database.
     */
    suspend fun addToFavorites(flatGif: FlatGif)

    /**
     * Removes the GIF from the favorites database.
     */
    suspend fun removeFromFavorites(flatGif: FlatGif)

}