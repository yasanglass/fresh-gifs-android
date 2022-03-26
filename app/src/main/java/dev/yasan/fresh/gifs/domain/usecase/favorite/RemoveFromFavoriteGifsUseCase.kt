package dev.yasan.fresh.gifs.domain.usecase.favorite

import dev.yasan.fresh.gifs.domain.repository.FavoriteRepository
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import javax.inject.Inject

/**
 * Use case for removing a gif from favorite list.
 */
class RemoveFromFavoriteGifsUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(gif: FlatGif) {
        favoriteRepository.removeFromFavorites(gif)
    }

}