package dev.yasan.fresh.gifs.data.repository

import androidx.lifecycle.LiveData
import dev.yasan.fresh.gifs.data.source.database.dao.FavoriteGifDao
import dev.yasan.fresh.gifs.domain.repository.FavoriteRepository
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import javax.inject.Inject

class FavoriteRepositoryImp @Inject constructor(
    private val favoriteGifDao: FavoriteGifDao
) : FavoriteRepository {

    override fun getFavoriteGifs(): LiveData<List<FlatGif>> {
        return favoriteGifDao.getFavorites()
    }

    override suspend fun addToFavorites(flatGif: FlatGif) {
        favoriteGifDao.insert(flatGif = flatGif)
    }

    override suspend fun removeFromFavorites(flatGif: FlatGif) {
        favoriteGifDao.delete(flatGif = flatGif)
    }

}