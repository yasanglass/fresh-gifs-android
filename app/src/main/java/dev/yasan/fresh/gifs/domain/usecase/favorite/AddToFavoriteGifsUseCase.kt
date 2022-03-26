package dev.yasan.fresh.gifs.domain.usecase.favorite

import dev.yasan.fresh.gifs.domain.repository.FavoriteRepository
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import javax.inject.Inject

/**
 * Use case for adding a gif to favorite list.
 */
class AddToFavoriteGifsUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(gif: FlatGif) {
        favoriteRepository.addToFavorites(gif)
    }

}