package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yasan.fresh.gifs.domain.usecase.favorite.AddToFavoriteGifsUseCase
import dev.yasan.fresh.gifs.domain.usecase.favorite.GetFavoriteGifsUseCase
import dev.yasan.fresh.gifs.domain.usecase.favorite.RemoveFromFavoriteGifsUseCase
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.search.SearchTab
import dev.yasan.kit.core.DispatcherProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A shared [ViewModel] for [SearchTab] & [FavoritesTab].
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val getFavoriteGifsUseCase: GetFavoriteGifsUseCase,
    private val addToFavoriteGifsUseCase: AddToFavoriteGifsUseCase,
    private val removeFromFavoriteGifsUseCase: RemoveFromFavoriteGifsUseCase
) : ViewModel() {

    val favoriteGifs get() = getFavoriteGifsUseCase()

    /**
     * Adds a [FlatGif] to the favorites.
     */
    fun addToFavorites(gif: FlatGif) {
        viewModelScope.launch(dispatchers.io) {
            addToFavoriteGifsUseCase(gif = gif)
        }
    }

    /**
     * Removes a [FlatGif] from the favorites.
     */
    fun removeFromFavorites(gif: FlatGif) {
        viewModelScope.launch(dispatchers.io) {
            removeFromFavoriteGifsUseCase(gif = gif)
        }
    }

}
