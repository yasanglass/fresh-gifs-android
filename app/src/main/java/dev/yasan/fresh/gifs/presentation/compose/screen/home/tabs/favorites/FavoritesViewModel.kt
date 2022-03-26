package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yasan.fresh.gifs.domain.repository.FavoriteRepository
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.kit.core.DispatcherProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] for [FavoritesTab].
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    companion object {
        private const val TAG = "FavoritesViewModel"
    }

    val favoriteGifs = favoriteRepository.getFavoriteGifs()

    /**
     * Adds a [FlatGif] to the favorites.
     */
    fun addToFavorites(flatGif: FlatGif) {
        viewModelScope.launch(dispatchers.io) {
            favoriteRepository.addToFavorites(flatGif)
        }
    }

    /**
     * Removes a [FlatGif] from the favorites.
     */
    fun removeFromFavorites(flatGif: FlatGif) {
        viewModelScope.launch(dispatchers.io) {
            favoriteRepository.removeFromFavorites(flatGif)
        }
    }

}
