package dev.yasan.fresh.gifs.presentation.compose.screen.home.modules

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import dev.yasan.fresh.gifs.model.freshgifs.HomeTab
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.favorites.FavoritesTab
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.favorites.FavoritesViewModel
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.search.SearchTab

private const val TAG = "HomePager"

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomePager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {

    val favoritesViewModel: FavoritesViewModel = hiltViewModel()

    val favoriteGifs = favoritesViewModel.favoriteGifs.observeAsState()

    val favoriteGifsList = favoriteGifs.value ?: emptyList()

    HorizontalPager(
        modifier = modifier,
        count = HomeTab.values().size,
        state = pagerState,
    ) { page ->
        when (page) {
            HomeTab.SEARCH.ordinal -> {
                SearchTab(
                    favoriteGifs = favoriteGifsList,
                    onAddToFavorites = { flatGif ->
                        favoritesViewModel.addToFavorites(flatGif = flatGif)
                    },
                    onRemoveFromFavorites = { flatGif ->
                        favoritesViewModel.removeFromFavorites(flatGif = flatGif)
                    }
                )
            }
            else -> {
                FavoritesTab(
                    favoriteGifs = favoriteGifsList,
                    onRemoveFromFavorites = { flatGif ->
                        favoritesViewModel.removeFromFavorites(flatGif = flatGif)
                    }
                )
            }
        }
    }

}