package dev.yasan.fresh.gifs.domain.usecase.favorite

import androidx.lifecycle.LiveData
import dev.yasan.fresh.gifs.domain.repository.FavoriteRepository
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import javax.inject.Inject

/**
 * Use case for getting favorite gifs.
 */
class GetFavoriteGifsUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    operator fun invoke(): LiveData<List<FlatGif>> {
        return favoriteRepository.getFavoriteGifs()
    }

}